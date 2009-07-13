story {
  title "A meeting"
  description "An exciting story about a meeting."
  startsAt "meetingRoom"
  
  room "meetingRoom" {
    title "Some place"
    description "This is some random place"
    action "view" { params, story -> story.viewRoom() }
    action "go" { params, story -> story.goToRoom(params[0]) }
  }
  
  room "office" {
    title "The End"
    description "Enough Said!"    
  }
}