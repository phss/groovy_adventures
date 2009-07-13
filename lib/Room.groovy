class Room {

  def story
  def title
  def description
  def actions = [:]
  def links = [:]
  
  public Room(story) {
    this.story = story
  }
  
  def display() {
    def description = "-- $title --\n\n$description"
    if (!links.isEmpty()) {
      description += "\n\nYou can go ${links.collect { direction, room -> "'$direction' to the $room" }.join(", ")}."
    }
    return description
  }

  def perform(action, params = []) {
    return actions?.containsKey(action) ? actions[action](params, story) : "Unknown action '$action'"
  }
  
  def roomFromLink(link) {
    links[link]
  }

}