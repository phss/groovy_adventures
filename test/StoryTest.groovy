import groovy.util.GroovyTestCase

class StoryTest extends GroovyTestCase {
  
  def storyConfig = [
    title: "Blah",
    description: "Some random story",
    startsAt: "first",
    rooms: [
      [
        id: "second",
        title: "Another place",
        description: "Another random place"
      ],    
      [
        id: "first",
        title: "Some place",
        description: "This is some random place",
        actions: [
          go: { story -> story.go_to_room("second") }
        ]
      ]
    ]
  ]
  
  void testConstructStory() {
    def story = new Story(storyConfig)
    
    assertEquals "Blah", story.title
    assertEquals "Some random story", story.description
    assert story.rooms.containsKey("first")
    assert story.rooms.containsKey("second")
    assertEquals "first", story.currentRoom.id    
  }
  
  void testDisplaysCurrentRoom() {
    def expectedDisplay = """-- Some place --

This is some random place
"""
    assertEquals expectedDisplay, new Story(storyConfig).viewRoom()
  }
  
  void testInterpretActionWithoutParamsFromCurrentRoom() {
    def story = new Story(storyConfig)
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
    def story = new Story(storyConfig)
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
    assertEquals "Unknown action 'test_action'", new Story(storyConfig).interpret("test_action")
  }
  
  void testInterpretBlankCommandReturnBlank() {
    assertEquals "", new Story(storyConfig).interpret("")    
  }
  
  void testGoToExistingRoom() {
    def story = new Story(storyConfig)
    story.goToRoom("second")
    
    assertEquals "second", story.currentRoom.id
  }
  
  void testGoingToAnUknownRoomDoesNotFail() {
    def story = new Story(storyConfig)
    story.goToRoom("unknown")
    
    assertEquals "first", story.currentRoom.id
  }
}