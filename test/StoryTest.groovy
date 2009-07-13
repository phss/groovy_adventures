import groovy.util.GroovyTestCase

class StoryTest extends GroovyTestCase {
  
  void testDisplaysCurrentRoom() {
    def expectedDisplay = """-- Some place --

This is some random place
"""
    assertEquals expectedDisplay, newStoryWithARoom("Some place", "This is some random place").viewRoom()
  }
  
  void testInterpretActionWithoutParamsFromCurrentRoom() {
    def story = newStoryWithARoom()
    story.currentRoom.actions += [
      test_action: { params, storyParam -> 
        assertEquals story, storyParam
        assertEquals([], params)
        "Action called"
      }
    ]
    
    assertEquals "Action called", story.interpret("test_action")
  }
  
  void testInterpretActionWithParamsFromCurrentRoom() {
    def story = newStoryWithARoom()
    story.currentRoom.actions += [
      test_action: { params, storyParam -> 
        assertEquals story, storyParam
        assertEquals(["param1", "param2"], params)
        "Action called"
      }
    ]
    
    assertEquals "Action called", story.interpret("test_action param1 param2")
  }
  
  void testInterpretUnknownActionShouldReturnUnknownAction() {
    assertEquals "Unknown action 'test_action'", newStoryWithARoom().interpret("test_action")
  }
  
  void testInterpretBlankCommandReturnBlank() {
    assertEquals "", newStoryWithARoom().interpret("")    
  }
  
  void testGoToExistingRoom() {
    def story = newStoryWithARoom("some room")
    def anotherRoom = new Room(story)
    anotherRoom.title = "another room"
    story.rooms += [ another: anotherRoom ]
    story.goToRoom("another")
    
    assertEquals "another room", story.currentRoom.title
  }
  
  void testGoingToAnUknownRoomDoesNotFail() {
    def story = newStoryWithARoom("some room")
    story.goToRoom("unknown")
    
    assertEquals "some room", story.currentRoom.title
  }
  
  def newStoryWithARoom(title = "", description = "") {
    def story = new Story()
    def room = new Room(story)
    room.title = title
    room.description = description
    story.rooms += [ room: room ]
    story.goToRoom("room")
    return story
  }
}