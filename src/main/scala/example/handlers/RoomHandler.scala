package example.handlers

import example.model.{Message, Room}

object RoomHandler {

  def handle(roomName: String): Room = {
    Room(roomName, List(Message("iirc admin", "Behave!")))
  }

}
