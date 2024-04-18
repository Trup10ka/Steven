package me.trup10ka.steven.server.config


import me.trup10ka.steven.server.util.CONFIG_TEMPLATE_PATH
import me.trup10ka.steven.server.util.configTemplateAsStream
import me.trup10ka.steven.server.util.parseFromYaml
import java.io.File
import java.io.InputStream
import java.nio.file.Files

class FileConfigProvider(private val path: String) : ConfigProvider
{
    override fun getConfig(): Config
    {
        val file = File(path)

        createConfigIfNotExists(
            configTemplateAsStream(CONFIG_TEMPLATE_PATH),
            file
        )

        val config = parseFromYaml(file.readText())
        return config
    }

    private fun createConfigIfNotExists(source: InputStream, destination: File)
    {
        if (destination.exists())
            return

        Files.copy(source, destination.toPath())
    }
}