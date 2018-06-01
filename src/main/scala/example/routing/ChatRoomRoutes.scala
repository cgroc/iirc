package example.routing

import cats.effect.IO
import example.handlers.RoomHandler
import example.model.{Message, Room}
import io.circe.syntax._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.{Request, Response}

object ChatRoomRoutes extends Http4sDsl[IO] {

  def routes(): PartialFunction[Request[IO], IO[Response[IO]]] = {

    case GET -> Root / "room" / name =>
      RoomHandler.getRoom(name) flatMap {
        r => Ok(r.messages.asJson)
      }

    case req@PUT -> Root / "room" / name =>
      req.attemptAs[Room].value flatMap {
        case Left(_) =>
          BadRequest()
        case Right(r) if r.name equals name =>
          RoomHandler.putRoomToCache(r) flatMap {
            _ => Created(r.asJson)
          }
        case Right(_) =>
          BadRequest("Resource name different from room name")
      }

    case req@POST -> Root / "room" / name =>
      req.attemptAs[Message].value flatMap {
        case Left(_) => BadRequest()
        case Right(m) =>
          RoomHandler.updateRoom(name, m) flatMap {
            r => Created(r.asJson)
          }

      }
  }

}
