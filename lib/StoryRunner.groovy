class StoryRunner {
  
  static void play(fileName) {
    def story = loadStory(new File(fileName))
    println story.viewRoom()
    
    while(!story.currentRoom.actions.isEmpty()) {
      print ">> "
      def result = story.interpret(System.in.newReader().readLine())
      if (result) {
        println "\n$result"
      }
    }
  }
  
  static Story loadStory(file) {
    Script dslScript = new GroovyShell().parse(file.text)
    Story story = new Story()

    dslScript.metaClass = createEMC(dslScript.class, { ExpandoMetaClass emc ->
        emc.story = { Closure cl ->
            cl.delegate = new StoryDelegate(story)
            cl.resolveStrategy = Closure.DELEGATE_FIRST
            cl()
        }
    })
    dslScript.run()
    
    return story
  }
  
  static ExpandoMetaClass createEMC(Class clazz, Closure cl) {
      ExpandoMetaClass emc = new ExpandoMetaClass(clazz, false)

      cl(emc)

      emc.initialize()
      return emc
  }

}