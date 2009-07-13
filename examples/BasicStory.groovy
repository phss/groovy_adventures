story {
  title "A meeting"
  description "An exciting story about a meeting."
    
  room("meetingRoom") {
    title "Meeting room"
    description """You are in the youDevise Ltd meeting room attending a retrospective meeting.
    
Everything seems really positive! The whiteboard if full of green stickies \
and everyone is pleased with the last release. It suddently dawns on you that this can't be real \
and you realise you are either dreaming or stuck in a stupid game.

You can sneak out of the meeting into the 'office'."""
    action("view") { params, story -> story.viewRoom() }
    action("go") { params, story -> story.goToRoom(params[0]); story.viewRoom() }
  }
  
  room("office") {
    title "Office"
    description """It looks quite empty, probably because all your team is in the retrospective.

You can go back to the 'meetingRoom' or flee through the 'exit'."""
    action("view") { params, story -> story.viewRoom() }
    action("go") { params, story -> story.goToRoom(params[0]); story.viewRoom() }
  }
  
  room("exit") {
    title "Exit"
    description "Goodbye!"
  }
  
  startsAt "meetingRoom"
}