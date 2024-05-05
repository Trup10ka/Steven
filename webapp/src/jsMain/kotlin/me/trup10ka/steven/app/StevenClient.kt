package me.trup10ka.steven.app

import me.trup10ka.steven.app.pages.InvitePage
import me.trup10ka.steven.app.pages.MapPage
import me.trup10ka.steven.app.pages.Page

class StevenClient(currentRoute: String)
{
    private lateinit var page: Page


    init
    {
        when (currentRoute)
        {
            "/" -> page = InvitePage()
            "/take-me-in" -> page = MapPage()

            else -> console.error("Unknown route: $currentRoute, anything regarding script will not work")
        }
    }

    fun setup()
    {
        if (::page.isInitialized)
            page.setupPage()
    }
}
