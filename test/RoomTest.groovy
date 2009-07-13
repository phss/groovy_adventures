import groovy.util.GroovyTestCase
import groovy.mock.interceptor.*

class RoomTest extends GroovyTestCase {
  
  def config = [
    id: "blah", 
    title: "Some room", 
    description: "You are in a very boring room"
  ]
  
  void testRoomAttributes() {
    def room = new Room(null, config)
    assertEquals "blah", room.id
    assertEquals "Some room", room.title
    assertEquals "You are in a very boring room", room.description
  }
  
  void testRoomDisplay() {
    def expectedDisplay = """-- Some room --

You are in a very boring room
"""
    assertEquals expectedDisplay, new Room(null, config).display()
  }
  
  void testPerformingAnAction() {
    def room = roomWithAction(null, [ some_action: { params, story -> "Action called" } ])     
    assertEquals "Action called", room.perform("some_action")
  }
  
  void testPerformActionWithParams() {
    def expectedParams = ["param1", "param2"]
    def room = roomWithAction(null, [ some_action: { params, story -> 
          assertEquals expectedParams, params
          "Action called"
        }])
    
    assertEquals "Action called", room.perform("some_action", expectedParams)
  }
  
  void testPerformActionWithStory() {
    def storyCalled = false
    def mockStory = [ go_to_room: { storyCalled = true } ]
    def room = roomWithAction(mockStory, [ some_action: { params, story -> story.go_to_room("some other room") }])
      
    room.perform("some_action")  
    assert storyCalled
  }
  
  void testPerformingAnUnknownActionReturnErrorMessage() {
    assertEquals "Unknown action 'unknown'", new Room(null, config).perform("unknown")
  }
  
  def roomWithAction(story, action) {
    new Room(story, config + [ actions: action ])
  }
  
}