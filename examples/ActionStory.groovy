actionsStory = [
  title: "Actions",
  description: "Some random story",
  startsAt: "center_room",
  
  globalActions: [
    go: { params, story -> story.go_to_room(story.currentRoom.directions[params[0]]) }
  ],
  
  aliases: [
    n: "go north",
    s: "go south",
    w: "go west",
    e: "go east"   
  ],
  
  rooms: [
    [
      id: "center_room",
      title: "Center",
      description: "This is the center",
      directions: [
        north: "north_room",
        south: "south_room"
      ]
    ],
    
    [
      id: "north_room",
      title: "North",
      description: "This is north of the center",
      directions: [
        south: "center_room"
      ]
    ],
    
    [
      id: "south_room",
      title: "South",
      description: "This is south of the center",
      directions: [
        north: "center_room"
      ]
    ]
  ]
]