package me.trup10ka.steven.server.util

import com.charleskorn.kaml.Yaml
import com.typesafe.config.ConfigUtil
import kotlinx.serialization.decodeFromString
import me.trup10ka.steven.server.config.Config
import java.io.InputStream

fun parseFromYaml(content: String) = Yaml.default.decodeFromString<Config>(content)

fun configTemplateAsStream(path: String): InputStream =
    ConfigUtil::class.java.getResourceAsStream(path) ?: throw IllegalStateException("Could not find config template")
