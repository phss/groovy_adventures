import groovy.util.GroovyTestCase

class InventoryIntegrationTest extends GroovyTestCase {
  
  def story
  
  void setUp() {
    story = StoryRunner.loadStory(new File("examples/InventoryStory.groovy"))
  }
  
  void testDisplayingItems() {
    assert story.interpret("view").contains("-- Meeting room --")
    assert story.interpret("view").contains("You see 'clip' and 'rubberBand' in this room.") // Crap message, I know.
  }

  void testEmptyInventoryWhenNoItems() {
    assert story.interpret("inventory").contains("Empty")
  }
  
  void testCannotGetUnavailableItem() {
    assert story.interpret("get blah").contains("No such item 'blah'")
    assert story.interpret("inventory").contains("Empty")
  }
  
  void testInventoryWithItem() {
    assert story.interpret("get clip")
    assert story.interpret("inventory").contains("clip - a paper clip")
  }
  
  void testCannotCombineItemsYouDontHave() {
    assert story.interpret("combine foo bar").contains("You don't have these items.")
  }
  
  void testCombiningItems() {
    assert story.interpret("get clip")
    assert story.interpret("get rubberBand")
    assert story.interpret("combine clip rubberBand").contains("You created a 'key'.")
    assert story.interpret("inventory").contains("key - a magic key")
  }
  
  void testCannotUseItemOnUnsupportedObject() {
    assert story.interpret("get clip")
    assert story.interpret("use clip blah").contains("You cannot use 'clip' on 'blah'.")
  }
  
  void testUsingAnItem() {
    assert story.interpret("get clip")
    assert story.interpret("get rubberBand")
    assert story.interpret("combine clip rubberBand")
    assert story.interpret("go south").contains("-- Office --")
    
    assert story.interpret("use key door").contains("-- Exit --")
  }

}