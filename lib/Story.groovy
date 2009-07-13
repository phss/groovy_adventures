class Story {

  def title
  def description
  def rooms = [:]
  def globalActions = [:]
  def alias = [:]
  def currentRoom
  
  def interpret(command) {
    if (alias.containsKey(command)) {
      command = alias[command]
    }
    
    def commandArray = command.tokenize()
    if (commandArray.isEmpty()) {
      return ""
    }
    
    def action = commandArray.first()
    def params = commandArray.size() == 1 ? [] : commandArray[1..commandArray.size()-1]
    
    return globalActions.containsKey(action) ?  globalActions[action](params, this) : currentRoom.perform(action, params)
  }
  
  def viewRoom() {
    currentRoom.display()
  }
  
  def goToRoom(roomName) {
    if (rooms.containsKey(roomName)) {
      currentRoom = rooms[roomName]
    }
  }
  
  def goToLink(link) {
    goToRoom(currentRoom.roomFromLink(link))
  }

  def viewActions() {
    def allActions = globalActions + currentRoom.actions
    return "Available actions:\n" + allActions.collect { action, closure -> "- ${action}\n"}.join()
  }

}