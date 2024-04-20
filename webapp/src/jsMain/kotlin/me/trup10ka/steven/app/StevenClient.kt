package me.trup10ka.steven.app

import me.trup10ka.steven.app.pages.InvitePage
import me.trup10ka.steven.app.pages.Page

class StevenClient(currentRoute: String)
{
    private lateinit var page: Page

    init
    {
        when (currentRoute)
        {
            "/index.html" -> page = InvitePage()

            else -> console.error("Unknown route: $currentRoute, anything regarding script will not work")
        }
    }
}