package me.trup10ka.steven.server

import io.ktor.server.application.*
import me.trup10ka.steven.server.event.EventManager
import me.trup10ka.steven.server.event.TeacherVanguard
import me.trup10ka.steven.server.plugins.configureContentNegotiation
import me.trup10ka.steven.server.plugins.configureRouting

fun Application.stevenModule()
{
    val teacherVanguard = TeacherVanguard()
    val eventManager = EventManager(teacherVanguard)

    configureContentNegotiation()
    configureRouting(eventManager, teacherVanguard)
}
