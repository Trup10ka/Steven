package me.trup10ka.steven.server.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.trup10ka.steven.server.routing.areYouATeacher
import me.trup10ka.steven.server.routing.lookForEvent
import me.trup10ka.steven.server.util.JS_DIR
import me.trup10ka.steven.server.util.STYLES_DIR
import me.trup10ka.steven.server.util.throwIfFileDoesNotExist
import java.io.File

fun Application.configureRouting()
{
    val stevenIndex = File("index.html")

    throwIfFileDoesNotExist(stevenIndex, "HTML", "index.html")

    routing {

        staticResources("/styles/", STYLES_DIR)
        staticResources("/scripts/", JS_DIR)

        get { call.respondFile(stevenIndex) }

        areYouATeacher()

        lookForEvent()
    }
}
