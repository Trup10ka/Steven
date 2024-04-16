package me.trup10ka.steven.server

import me.trup10ka.shared.HOST
import me.trup10ka.shared.SERVER_PORT
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.trup10ka.steven.server.plugins.configureRouting

fun main()
{
    embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = HOST,
        module = Application::module
    ).start(wait = true)
}

fun Application.module()
{
    configureRouting()
}
