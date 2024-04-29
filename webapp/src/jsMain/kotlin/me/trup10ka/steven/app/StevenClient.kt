package me.trup10ka.steven.app

import kotlinx.browser.window
import me.trup10ka.shared.util.IdType.MEMBER
import me.trup10ka.shared.util.idOf
import me.trup10ka.steven.app.pages.InvitePage
import me.trup10ka.steven.app.pages.MapPage
import me.trup10ka.steven.app.pages.Page

class StevenClient(currentRoute: String)
{
    private lateinit var page: Page

    val memberId: String
        get() = getLastPathSegment() idOf MEMBER

    init
    {
        when (currentRoute)
        {
            "/" -> page = InvitePage()
            "/take-me-in" -> page = MapPage(this)

            else -> console.error("Unknown route: $currentRoute, anything regarding script will not work")
        }
    }

    fun setup()
    {
        if (::page.isInitialized)
            page.setupPage()
    }

    private fun getLastPathSegment(): String
    {
        return window.location.pathname.split("/").last()
    }
}
