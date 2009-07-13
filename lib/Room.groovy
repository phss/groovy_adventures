class Room {

  def story
  def id
  def title
  def description
  def actions
  
  public Room(story, roomConfig) {
    this.story = story
    
    id = roomConfig.id
    title = roomConfig.title
    description = roomConfig.description
    actions = roomConfig.actions
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