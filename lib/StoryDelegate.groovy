class StoryDelegate {

  def story

  public StoryDelegate(story) {
    this.story = story
  }
  
  void title(String title) {
    story.title = title
  }
  
  void description(String description) {
    story.description = description
  }
  
  void globalAction(String name, Closure action) {
    story.globalActions += [ (name): action ]
  }
  
  void alias(alias, command) {
    story.alias += [ (alias): command ]
  }
  
  void startsAt(roomId) {
    story.goToRoom(roomId)
  }
  
  void room(String id, Closure roomClosure) {
    def room = new Room(story)

    roomClosure.delegate = new RoomDelegate(room)
    roomClosure.resolveStrategy = Closure.DELEGATE_FIRST
    roomClosure()    
    
    story.rooms += [ (id): room ]
  }
  

}