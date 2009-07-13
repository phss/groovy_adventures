class RoomDelegate {

  def room

  public RoomDelegate(room) {
    this.room = room
  }

  void title(String title) {
    room.title = title
  }
  
  void description(String description) {
    room.description = description
  }
  
  void link(name, aRoom) {
    room.links += [ (name): aRoom ]
  }
  
  void action(String name, Closure action) {
    room.actions += [ (name): action ]
  }

}