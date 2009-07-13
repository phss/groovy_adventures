class Story {

  def title
  def description
  def rooms = [:]
  def currentRoom
  
  def interpret(command) {
    def commandArray = command.tokenize()
    if (commandArray.isEmpty()) {
      return ""
    }
    def action = commandArray.first()
    def params = commandArray.size() == 1 ? [] : commandArray[1..commandArray.size()-1]
    return currentRoom.perform(action, params)
  }
  
  def viewRoom() {
    currentRoom.display()
  }
  
  def goToRoom(roomName) {
    if (rooms.containsKey(roomName)) {
      currentRoom = rooms[roomName]
    }
  }

}