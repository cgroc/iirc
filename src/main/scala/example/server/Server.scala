package example.server

import cats.effect.IO
import example.routing.ChatRoomRoutes
import fs2.StreamApp
import fs2.StreamApp.ExitCode
import org.http4s.HttpService
import org.http4s.dsl.Http4sDsl
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext.Implicits.global

object Server extends StreamApp[IO] with Http4sDsl[IO] {

  val helloWorldService: HttpService[IO] = HttpService[IO] {
      ChatRoomRoutes.routes()
  }

  override def stream(args: List[String], requestShutdown: IO[Unit]): fs2.Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .bindHttp(8080, "0.0.0.0")
      .mountService(helloWorldService, "/")
      .serve
}
