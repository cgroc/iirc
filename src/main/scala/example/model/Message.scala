package example.model

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class Message(from: String, message: String)

object Message {

  implicit val messageDecoder: Decoder[Message] = deriveDecoder[Message]

  implicit val messageEncoder: Encoder[Message] = deriveEncoder[Message]
}

case class Room(name: String, messages: List[Message])

object Room {

  implicit val roomDecoder: Decoder[Room] = deriveDecoder[Room]

  implicit val roomEncoder: Encoder[Room] = deriveEncoder[Room]
}