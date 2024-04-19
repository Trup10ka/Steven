package me.trup10ka.steven.server

import io.ktor.server.application.*
import me.trup10ka.steven.server.plugins.configureRouting


fun Application.stevenModule()
{
    configureRouting()
}
