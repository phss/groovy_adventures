basicStory = [
  title: "Blah",
  description: "Some random story",
  startsAt: "start",
  rooms: [
    [
      id: "start",
      title: "Some place",
      description: "This is some random place",
      actions: [
        go: { params, story -> story.goToRoom(params[0]); story.viewRoom() },
        view: { params, story -> story.viewRoom() }
      ]
    ],
    [
      id: "exit",
      title: "The End",
      description: "Enough Said!"
    ]
  ]
]


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

/*StoryRunner.play(basicStory)*/

StoryRunner.play("examples/BasicStory.groovy")