package me.trup10ka.steven.server.config

interface ConfigProvider
{
    fun getConfig(): Config
}