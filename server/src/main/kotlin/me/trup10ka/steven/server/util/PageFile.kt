package me.trup10ka.steven.server.util

import java.io.File

/**
 * Utility object for loading HTML files from the resources directory.
 *
 * @see [me.trup10ka.steven.server.plugins.pages]
 * @since 1.0.0
 */
object PageFile
{
    val stevenIndex = fromResources("index.html")
    val mapPage = fromResources("pages/map.html")
    val createPage = fromResources("pages/create-event.html")
}
