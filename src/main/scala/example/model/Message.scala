package example.model

import cats.effect.IO
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import org.http4s.EntityDecoder
import org.http4s.circe.jsonOf

case class Message(from: String, message: String)

object Message {

  implicit val messageDecoder: Decoder[Message] = deriveDecoder[Message]

  implicit val messageEncoder: Encoder[Message] = deriveEncoder[Message]

  implicit val entityDecoderMessage: EntityDecoder[IO, Message] =
    jsonOf[IO, Message]
}

case class Room(name: String, messages: List[Message])

object Room {

  implicit val roomDecoder: Decoder[Room] = deriveDecoder[Room]

  implicit val roomEncoder: Encoder[Room] = deriveEncoder[Room]

  implicit val entityDecoderMessage: EntityDecoder[IO, Room] =
    jsonOf[IO, Room]
}