import groovy.util.GroovyTestCase

class ActionIntegrationTest extends GroovyTestCase {
  
  def story
  
  void setUp() {
    story = StoryRunner.loadStory(new File("examples/ActionStory.groovy"))
  }
  
  void testLinkNavigation() {
    assert story.interpret("view").contains("-- Meeting room --")

    assert story.interpret("go office").contains("-- Meeting room --")
    
    assert story.interpret("go south").contains("-- Office --")
    assert story.interpret("go north").contains("-- Meeting room --")
    
    assert story.interpret("go south").contains("-- Office --")
    assert story.interpret("go south").contains("-- Office --")
    assert story.interpret("go west").contains("-- Office --")
    assert story.interpret("go east").contains("-- Exit --")
  }
  
  void testAliases() {
    assert story.interpret("view").contains("-- Meeting room --")

    assert story.interpret("s").contains("-- Office --")
    assert story.interpret("n").contains("-- Meeting room --")
    
    assert story.interpret("s").contains("-- Office --")
    assert story.interpret("s").contains("-- Office --")
    assert story.interpret("w").contains("-- Office --")
    assert story.interpret("e").contains("-- Exit --")
  }
  
  void testHelp() {
    assert story.interpret("view").contains("-- Meeting room --")
    assert story.interpret("help").contains("view")
    assert story.interpret("help").contains("go")
    assert story.interpret("help").contains("help")
    assert story.interpret("help").contains("ask")
    
    assert story.interpret("s").contains("-- Office --")
    assert story.interpret("help").contains("view")
    assert story.interpret("help").contains("go")
    assert story.interpret("help").contains("help")
    assert !story.interpret("help").contains("ask")    
  }
}