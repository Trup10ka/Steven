package me.trup10ka.steven.server.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import me.trup10ka.steven.server.event.EventManager
import me.trup10ka.steven.server.event.TeacherVanguard
import me.trup10ka.steven.server.util.fromResources
import me.trup10ka.steven.server.util.throwIfFileDoesNotExist

fun Application.configureRouting(
    eventManager: EventManager,
    teacherVanguard: TeacherVanguard
)
{
    routing {

        resourceFiles()

        pages(teacherVanguard, eventManager)

        api(teacherVanguard, eventManager)
    }
}

private fun Route.pages(teacherVanguard: TeacherVanguard, eventManager: EventManager)
{
    route("/") {
        index()
        areYouATeacher(teacherVanguard)
        lookForEvent(eventManager, teacherVanguard)
    }
}

private fun Route.api(teacherVanguard: TeacherVanguard, eventManager: EventManager)
{
    route("/api") {
        getAllMembersFromEvent(eventManager)
        createNewEvent(eventManager, teacherVanguard)
    }
}

private fun Route.resourceFiles()
{
    val staticStyles = fromResources("styles")
    val staticScripts = fromResources("scripts")

    throwIfFileDoesNotExist(staticStyles, "Directory", "styles")
    throwIfFileDoesNotExist(staticScripts, "Directory", "scripts")

    staticFiles("/styles/", staticStyles)
    staticFiles("/scripts/", staticScripts)
}
