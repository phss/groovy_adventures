class Room {

  def story
  def title
  def description
  def actions = [:]
  
  public Room(story) {
    this.story = story
  }
  
  def display() {
    """-- $title --

$description
"""
  }

  def perform(action, params = []) {
    return actions?.containsKey(action) ? actions[action](params, story) : "Unknown action '$action'"
  }

}