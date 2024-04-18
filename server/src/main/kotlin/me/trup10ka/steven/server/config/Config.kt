package me.trup10ka.steven.server.config

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val port: Int,
    val host: String
)
{
    override fun toString(): String
    {
        return "Config(serverPort=$port, host='$host')"
    }
}
