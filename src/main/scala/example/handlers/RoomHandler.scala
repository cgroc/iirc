package example.handlers

import cats.effect.IO
import example.model.{Message, Room}
import scalacache._
import scalacache.caffeine._
import scala.language.implicitConversions
import scalacache.CatsEffect.modes._



object RoomHandler {

  def handle(roomName: String): Room = {
    Room(roomName, List(Message("iirc admin", "Behave!")))
  }

  implicit val caffeineCache: Cache[Room] = CaffeineCache[Room]

  def putRoom(room: Room): IO[Any] =
    put(room.name)(room)

  def getRoom(name: String): IO[RoomFromStorageResult] =
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
}


