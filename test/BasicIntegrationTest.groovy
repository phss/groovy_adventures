import groovy.util.GroovyTestCase

class BasicIntegrationTest extends GroovyTestCase {
  
  void testBasicIntegration() {
    def story = StoryRunner.loadStory(new File("examples/BasicStory.groovy"))
    
    assert story.viewRoom().contains("-- Meeting room --")
    assert story.interpret("view").contains("-- Meeting room --")

    assert story.interpret("blah").contains("Unknown action")
    assert story.interpret("view").contains("-- Meeting room --")
    
    assert story.interpret("go office").contains("-- Office --")
    assert story.interpret("go meetingRoom").contains("-- Meeting room --")
    story.interpret("go office")
    
    assert story.interpret("go exit").contains("-- Exit --")
    assert story.interpret("go office").contains("Unknown action")
  }
}