package me.trup10ka.steven.server.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.trup10ka.steven.server.event.TeacherVanguard
import me.trup10ka.steven.server.util.fromResources
import me.trup10ka.steven.server.util.throwIfFileDoesNotExist
import java.io.File

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
