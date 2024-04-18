package me.trup10ka.steven.server.routing

import io.ktor.server.routing.*
import me.trup10ka.steven.server.util.throwIfFileDoesNotExist
import java.io.File
import java.io.FileNotFoundException

fun Route.lookForEvent()
{
    val mapPage = File("resources/pages/map.html")

    throwIfFileDoesNotExist(mapPage, "HTML", "map.html")

    get("/take-me-in") {

    }
}
