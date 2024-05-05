package me.trup10ka.steven.server.util

import java.io.File

object PageFile
{
    val stevenIndex: File = fromResources("index.html")
    val mapPage = fromResources("pages/map.html")
    val createPage = fromResources("pages/create-event.html")
}
