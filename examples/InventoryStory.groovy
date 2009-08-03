story {
  title "A meeting"
  description "An exciting story about a meeting."
  
  globalAction("view") { params, story -> story.viewRoom() }
  globalAction("go") { params, story -> story.goToLink(params[0]); story.viewRoom() }
  globalAction("inventory") { params, story -> story.showInventory() }
  globalAction("get") { params, story -> story.get(params[0]) }
  globalAction("combine") { params, story -> story.combine(params[0], params[1]) }  
  globalAction("use") { params, story -> story.use(params[0], params[1]) }
  globalAction("help") { params, story -> story.viewActions() }
  
  alias "n", "go north"
  alias "s", "go south"
  alias "w", "go west" 
  alias "e", "go east"
  alias "i", "inventory"
    
  room("meetingRoom") {
    title "Meeting room"
    description """You are in the youDevise Ltd meeting room attending a retrospective meeting.
    
Everything seems really positive! The whiteboard is full of green stickies \
and everyone is pleased with the last release. It suddently dawns on you that this can't be real \
and you realise you are either dreaming or stuck in a stupid game."""

    item "paperClip", "at the table"
    item "rubberBand", "on the floor"

    link "south", "office"

    action("ask") { params, story -> "You ask '${params.join(" ")}' but everyone ignores you. They are too busy \
praising the browser build speeds. It is under 5 minutes! This is clearly a dream." }
  }
  
  room("office") {
    title "Office"
    description "It looks quite empty, probably because all your team is in the retrospective."

    item "door", "which is locked"
    
    use("clips", "door") { story -> story.goToRoom("exit") }
    
    link "north", "meetingRoom"
  }
  
  room("exit") {
    title "Exit"
    description "Goodbye!"
  }
  
  startsAt "meetingRoom"
}