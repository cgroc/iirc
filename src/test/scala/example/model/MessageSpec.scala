package example.model

import org.scalatest.{Matchers, WordSpec}
import io.circe.parser._
import io.circe.syntax._

class MessageSpec extends WordSpec with Matchers {

  val validMessageString: String = """{"from": "dave", "message": "Use wartremover!"}"""
  val validMessage: Message = Message(from = "dave", message = "Use wartremover!")

  "Message" should {

    "decode from a valid String" in {

      decode[Message](validMessageString) shouldBe Right(validMessage)
    }

    "encode to a valid string" in {

      validMessage.asJson.noSpaces.filter(!_.isWhitespace) shouldBe validMessageString.filter(!_.isWhitespace)
    }
  }
}
