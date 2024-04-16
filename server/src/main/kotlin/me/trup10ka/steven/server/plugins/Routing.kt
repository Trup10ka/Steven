package me.trup10ka.steven.server.plugins

import me.trup10ka.shared.JS_DIR
import me.trup10ka.shared.STYLES_DIR
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting()
{
    val stevenIndex = File("index.html")
    val mapPage = File("resources/pages/map.html")
    val createPage = File("resources/pages/create-event.html")

    routing {

        staticResources("/styles/", STYLES_DIR)
        staticResources("/scripts/", JS_DIR)

        get {
            call.respondFile(stevenIndex)
        }

        get("/take-me-in") {
            call.respondFile(mapPage)
        }

        get("/are-you-a-teacher") {
            call.respondFile(mapPage)
        }
    }
}
