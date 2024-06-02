package me.trup10ka.steven.server

import io.ktor.server.application.*
import me.trup10ka.steven.server.event.EventManager
import me.trup10ka.steven.server.event.TeacherVanguard
import me.trup10ka.steven.server.plugins.configureContentNegotiation
import me.trup10ka.steven.server.plugins.configureRouting

/**
 * Module for the Steven server app.
 *
 * This module configures the server's content negotiation and routing.
 *
 * During the module initialization, an [EventManager] and a [TeacherVanguard] are created.
 *
 * @since 1.0.0
 */
fun Application.stevenModule()
{
    val teacherVanguard = TeacherVanguard()
    val eventManager = EventManager(teacherVanguard)

    configureContentNegotiation()
    configureRouting(eventManager, teacherVanguard)
}
