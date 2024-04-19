package me.trup10ka.steven.server.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.trup10ka.steven.server.event.EventManager
import me.trup10ka.steven.server.util.throwIfFileDoesNotExist
import java.io.File

fun Route.lookForEvent(eventManager: EventManager)
{
    val mapPage = File("resources/pages/map.html")

    throwIfFileDoesNotExist(mapPage, "HTML", "map.html")

    get("/take-me-in") {
        call.respondFile(mapPage)
    }
}
