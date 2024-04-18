package me.trup10ka.steven.server.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.trup10ka.steven.server.util.throwIfFileDoesNotExist
import java.io.File

fun Route.areYouATeacher()
{
    val createPage = File("resources/pages/create-event.html")

    throwIfFileDoesNotExist(createPage, "HTML", "create-event.html")

    get("/are-you-a-teacher") {
        call.respondFile(createPage)
    }
}
