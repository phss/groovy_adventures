import groovy.util.GroovyTestCase
import groovy.mock.interceptor.*

class RoomTest extends GroovyTestCase {
  
  void testRoomDisplay() {
    def expectedDisplay = """-- Some room --

You are in a very boring room.
"""
    def room = new Room(null)
    room.title = "Some room"
    room.description = "You are in a very boring room."
    assertEquals expectedDisplay, room.display()
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
    assertEquals "Unknown action 'unknown'", new Room(null).perform("unknown")
  }
  
  def roomWithAction(story, action) {
    def room = new Room(story)
    room.actions += action
    return room
  }
  
}