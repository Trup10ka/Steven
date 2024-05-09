package me.trup10ka.steven.app

import me.trup10ka.shared.util.second
import me.trup10ka.steven.app.pages.CreateEventPage
import me.trup10ka.steven.app.pages.InvitePage
import me.trup10ka.steven.app.pages.MapPage
import me.trup10ka.steven.app.pages.Page

class StevenClient(currentRoute: String)
{
    private lateinit var page: Page

    init
    {
        val rootCurrentRoute = currentRoute.split("/").second()
        when (rootCurrentRoute)
        {
            "" -> page = InvitePage()
            "take-me-in" -> page = MapPage()
            "are-you-a-teacher" -> page = CreateEventPage()

            else -> console.error("Unknown route: $currentRoute, anything using script will not work")
        }
    }

    fun setup()
    {
        if (::page.isInitialized)
            page.setupPage()
    }
}
