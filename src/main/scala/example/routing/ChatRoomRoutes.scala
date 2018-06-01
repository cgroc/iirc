package example.routing

import cats.effect.IO
import example.handlers.RoomHandler
import org.http4s.{Request, Response}
import org.http4s.dsl.Http4sDsl
import io.circe.syntax._
import org.http4s.circe._


object ChatRoomRoutes extends Http4sDsl[IO] {

  def routes(): PartialFunction[Request[IO], IO[Response[IO]]] = {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name.")

    case GET -> Root / "room" / name =>
      RoomHandler.getRoom(name) flatMap {
        r => Ok(r.messages.asJson)
      }


    case PUT -> Root / "room" =>
      Created(s"Your room was created")

    case POST -> Root / "room" / name =>
      Created(s"Your message was posted to $name")
  }

}
