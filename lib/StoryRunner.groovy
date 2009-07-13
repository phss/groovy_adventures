class StoryRunner {

  static void play(storyConfig) {
    def story = new Story(storyConfig)    
    println story.viewRoom()
    
    while(story.currentRoom.actions != null) {
      print ">> "
      def result = story.interpret(System.in.newReader().readLine())
      if (result) {
        println "\n$result"
      }
    }
  }

}