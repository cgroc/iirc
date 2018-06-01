package example.handlers

import cats.effect.IO
import example.model.{Message, Room}
import scalacache._
import scalacache.caffeine._
import scala.language.implicitConversions
import scalacache.CatsEffect.modes._

object RoomHandler {

  implicit val caffeineCache: Cache[Room] = CaffeineCache[Room]

  def putRoomToCache(room: Room): IO[Any] =
    put(room.name)(room)

  def getRoomFromCahe(name: String): IO[RoomFromStorageResult] =
    get[IO, Room](name)

  sealed trait RoomFromStorageResult

  case object NoRoomFound extends RoomFromStorageResult

  case class RoomFound(room: Room) extends RoomFromStorageResult

  object RoomFromStorageResult {

    implicit def optionToResult(om: IO[Option[Room]]): IO[RoomFromStorageResult] =
      om.map {
        case Some(m) =>
          RoomFound(m)

        case None =>
          NoRoomFound
      }
  }

  def getRoom(roomName: String): IO[Room] = {
    getRoomFromCahe(roomName).flatMap {
      case NoRoomFound => makeRoomAndPut(roomName)
      case RoomFound(r) => IO.pure(r)
    }
  }

  def makeRoomAndPut(roomName: String): IO[Room] = {
    val newRoom: Room = Room(roomName, List.empty)
    putRoomToCache(newRoom) map {
      _ => newRoom
    }
  }

  def updateRoom(roomName: String, message: Message): IO[Room] =
    getRoom(roomName) map {
      r => {
        val updated: Room = r.copy(messages = r.messages :+ message)
        putRoomToCache(updated).unsafeRunSync
        updated
      }
    }

}


