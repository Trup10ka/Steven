package me.trup10ka.steven.server.config


import me.trup10ka.steven.server.util.CONFIG_TEMPLATE_PATH
import me.trup10ka.steven.server.util.configTemplateAsStream
import me.trup10ka.steven.server.util.copyFileIfNotExists
import me.trup10ka.steven.server.util.parseFromYaml
import java.io.File

class FileConfigProvider(private val path: String) : ConfigProvider
{
    override fun getConfig(): Config
    {
        val file = File(path)

        copyFileIfNotExists(
            configTemplateAsStream(CONFIG_TEMPLATE_PATH),
            file
        )

        val config = parseFromYaml(file.readText())
        return config
    }
}
