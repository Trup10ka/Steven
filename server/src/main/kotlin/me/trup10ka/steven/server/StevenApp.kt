package me.trup10ka.steven.server

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.trup10ka.steven.server.config.Config
import me.trup10ka.steven.server.config.FileConfigProvider

class StevenApp
{
    private lateinit var config: Config

    private lateinit var engine: ApplicationEngine

    private val configProvider = FileConfigProvider("config.yaml")

    fun init()
    {
        loadConfig()
        setupEngine()
    }

    fun start() = engine.start(wait = true)

    private fun loadConfig()
    {
        config = configProvider.getConfig()
    }

    private fun setupEngine()
    {
        engine = embeddedServer(
            Netty,
            port = config.port,
            host = config.host,
            module = Application::stevenModule
        )
    }
}
