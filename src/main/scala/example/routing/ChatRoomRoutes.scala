package example.routing

import cats.effect.IO
import example.handlers.RoomHandler
import example.model.Message
import io.circe.syntax._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.{EntityDecoder, Request, Response}


object ChatRoomRoutes extends Http4sDsl[IO] {

  implicit val entityDecoderMessage: EntityDecoder[IO, Message] =
    jsonOf[IO, Message]

  def routes(): PartialFunction[Request[IO], IO[Response[IO]]] = {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name.")

    case GET -> Root / "room" / name =>
      RoomHandler.getRoom(name) flatMap {
        r => Ok(r.messages.asJson)
      }

    case PUT -> Root / room =>
      RoomHandler.makeRoomAndPut(room) flatMap[Response[IO]] {
        r => Created(r.asJson)
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
