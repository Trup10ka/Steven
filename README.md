# Steven
**Kotlin Web application**

This app is supposed to keep track of people on various group events.

 > *This project exists thanks to my Web technologies professor, who gave me the idea to create this app, and also to my 
love for Kotlin.*

## General idea
One person from the group, who is chosen as the event organizer. 
He then creates an event and invites people to join it.

If the receiver accepts the invitation, he is added to the event list.
Organiser then can see who is where and when was the last time they checked in.

Basically, **non-admin user can only see map and where the user stands and a RED button sayin "I'm alive!"**
After pressing this button, your position is updated and the last time-check is updated.

So the organizer can keep in touch with the group and see if everyone is okay.

## Kotlin multiplatform target
This is a Kotlin Multiplatform project targeting Web, Server.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, `jsMain` is folder for JavaScript.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
