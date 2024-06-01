# Steven
**Kotlin Web application**

This app is supposed to keep track of people on various group events.

 > *This project exists thanks to my Web technologies professor, who gave me the idea to create this app, and also to my 
love for Kotlin.*

---

## General idea
One person from the group,  is chosen as the event organizer. 
Event organizer creates an event and creates place for people to join the event.

Each event has a name, date, time, and a list of people who are going to attend the event.
Each person has a name, email, and phone number and ID, which event organizer shares among the group.

The whole Event interface is supposed to be simple and easy to use.
When client opens the event, a page with a map and list of all members connected to that event will be shown.

Client then can send his current location via "I'm alive!" button, which will update his location on the map.
Client can request location of other members via "Are they alive?" button, to refresh the event page

### Learn more about [Steven here]()

---

## Build and run
This application consists of three separate modules:
- `webApp` - Kotlin/JS module for the client side of the application
- `server` - Kotlin/JVM module for the server side of the application
- `shared` - Kotlin common module for shared code between client and server

To build and run the application, you would need to build all the modules and then after that combine them together.

There is a **python** script prepared for this task, to ease up the process.

You can see the python script here: [assemble.py](https://github.com/Trup10ka/Steven/blob/main/assemble.py)

### Requirements
- [Java 17](https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.exe)
- Kotlin 1.9.22 or higher 
- *Python 3 or higher (optional for running the build and run script)*

---

## Kotlin multiplatform target
This is a Kotlin Multiplatform project targeting Web, Server.

* `/webApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, `jsMain` is folder for JavaScript.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
