package me.trup10ka.steven.app

import kotlinx.browser.window

fun main()
{
    val stevenClient = StevenClient(window.location.pathname)
}
