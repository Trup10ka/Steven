package me.trup10ka.steven.server.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.trup10ka.steven.server.event.EventManager
import me.trup10ka.steven.server.event.TeacherVanguard
import me.trup10ka.steven.server.util.JS_DIR
import me.trup10ka.steven.server.util.STYLES_DIR
import me.trup10ka.steven.server.util.fromResources
import me.trup10ka.steven.server.util.throwIfFileDoesNotExist

fun Application.configureRouting(
    eventManager: EventManager,
    teacherVanguard: TeacherVanguard
)
{
    routing {

        staticResources("/styles/", STYLES_DIR)
        staticResources("/scripts/", JS_DIR)

        index()

        areYouATeacher(teacherVanguard)

        lookForEvent(eventManager)
    }
}

fun Route.index()
{
    val stevenIndex = fromResources("index.html")

    throwIfFileDoesNotExist(stevenIndex, "HTML", "index.html")

    get {
        call.respondFile(stevenIndex)
    }
}

fun Route.areYouATeacher(teacherVanguard: TeacherVanguard)
{
    val createPage = fromResources("pages/create-event.html")

    throwIfFileDoesNotExist(createPage, "HTML", "create-event.html")

    get("/are-you-a-teacher/{id}") {

        if (!teacherVanguard.isTeacher(call.parameters["id"]!!))
            call.respond(489)
        else
            call.respondFile(createPage)
    }
}

fun Route.lookForEvent(eventManager: EventManager)
{
    val mapPage = fromResources("pages/map.html")

    throwIfFileDoesNotExist(mapPage, "HTML", "map.html")

    get("/take-me-in") {
        call.respondFile(mapPage)
    }
}
