package example.model

import org.scalatest.{Matchers, WordSpec}
import io.circe.parser._
import io.circe.syntax._

class RoomSpec extends WordSpec with Matchers {

  val validRoomString: String =
    """
      |{
      | "name": "room101",
      | "messages": [
      |   {"from":"yuri","message":"Eat your vegetables"},
      |   {"from":"chris","message":"Stop doing that"}
      | ]
      |}
    """.stripMargin

  val validRoom: Room =
    Room(
      name = "room101",
      messages = List(
        Message(from = "yuri", message = "Eat your vegetables"),
        Message(from = "chris", message = "Stop doing that")
      )
    )

  "Room" should {

    "decode from a valid String" in {

      decode[Room](validRoomString) shouldBe Right(validRoom)
    }

    "encode to a valid string" in {

      validRoom.asJson.noSpaces.filter(!_.isWhitespace) shouldBe validRoomString.filter(!_.isWhitespace)
    }
  }

}
