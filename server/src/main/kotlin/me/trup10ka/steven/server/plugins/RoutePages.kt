package me.trup10ka.steven.server.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.trup10ka.steven.server.event.EventManager
import me.trup10ka.steven.server.event.TeacherVanguard
import me.trup10ka.steven.server.util.PageFile.createPage
import me.trup10ka.steven.server.util.PageFile.mapPage
import me.trup10ka.steven.server.util.PageFile.stevenIndex
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

    get("/take-me-in") {
        call.respondFile(mapPage)
    }

    get("take-me-in/{eventId}-{memberId}") {
        call.respondFile(mapPage)
    }
}
