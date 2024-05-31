package me.trup10ka.steven.server.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.trup10ka.shared.util.IdType
import me.trup10ka.shared.util.IdType.*
import me.trup10ka.shared.util.idOf
import me.trup10ka.steven.server.event.EventManager
import me.trup10ka.steven.server.event.TeacherVanguard
import me.trup10ka.steven.server.util.PageFile.createPage
import me.trup10ka.steven.server.util.PageFile.mapPage
import me.trup10ka.steven.server.util.PageFile.stevenIndex
import me.trup10ka.steven.server.util.checkIfMemberIsPartOfEvent
import me.trup10ka.steven.server.util.throwIfFileDoesNotExist

fun Route.index()
{
    throwIfFileDoesNotExist(stevenIndex, "HTML", "index.html")

    get {
        call.respondFile(stevenIndex)
    }
}

fun Route.areYouATeacher(teacherVanguard: TeacherVanguard)
{
    throwIfFileDoesNotExist(createPage, "HTML", "create-event.html")

    get("/are-you-a-teacher/{id}") {

        if (!teacherVanguard.isTeacher(call.parameters["id"]!!))
            call.respond(HttpStatusCode.Unauthorized, "You are not a teacher")
        else
            call.respondFile(createPage)
    }
}

fun Route.lookForEvent(eventManager: EventManager, teacherVanguard: TeacherVanguard)
{
    throwIfFileDoesNotExist(mapPage, "HTML", "map.html")

    get("take-me-in/{id}") {

        val id = call.parameters["id"]!!
        val event = eventManager.getEventById(id)

        if (event == null)
        {
            call.respond(HttpStatusCode.NotFound, "Event not found")
            return@get
        }

        if (checkIfMemberIsPartOfEvent(event, id idOf MEMBER))
            call.respondFile(mapPage)
        else
            call.respond(HttpStatusCode.Unauthorized, "You are not part of this event")
    }
}
