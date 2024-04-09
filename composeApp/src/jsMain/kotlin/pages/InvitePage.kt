package pages

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import org.w3c.fetch.RequestInit
import util.post
import kotlin.js.json

object InvitePage
{
    private val invitationLinkField = document.getElementById("invitation-link-input") as HTMLInputElement

    private val takeMeInButton = document.getElementById("take-me-in-button") as HTMLButtonElement

    private val teacherButton = document.getElementById("are-you-a-teacher-button") as HTMLButtonElement

    private val invitationLink: String
        get() = invitationLinkField.value

    fun setUpButtons()
    {
        takeMeInButton.onclick = {
            suspend { sendProvidedLink() }
        }

        teacherButton.onclick = {
            suspend { sendProvidedLink() }
        }
    }

    private suspend fun sendProvidedLink()
    {
        val request = RequestInit(
            method = "GET",
            headers = json("Content-Type" to "application/json"),
            body = JSON.stringify(json("invitationLink" to invitationLink))
        )

        // val response = fetch("POST", "https://s-friedl.dev.spsejecna.net/link", request)
        val response = post("http://localhost/link", request)
    }

    private fun redirectToTeacherPage()
    {
        window.location.href += "teacher.html"
    }
}
