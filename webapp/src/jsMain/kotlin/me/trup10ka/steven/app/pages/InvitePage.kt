package me.trup10ka.steven.app.pages

import kotlinx.browser.document
import kotlinx.browser.window
import me.trup10ka.shared.util.Color
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import me.trup10ka.steven.app.util.get
import me.trup10ka.steven.app.util.launchInMainScope


object InvitePage
{
    private val invitationLinkField = document.getElementById("invitation-link-input") as HTMLInputElement

    private val invitationLinkPrompt = document.getElementById("invitation-link-prompt") as HTMLElement

    private val takeMeInButton = document.getElementById("take-me-in-button") as HTMLButtonElement

    private val teacherButton = document.getElementById("are-you-a-teacher-button") as HTMLButtonElement

    private val invitationLink: String
        get() = invitationLinkField.value

    fun setUpButtons()
    {
        takeMeInButton.addEventListener("click", {
                launchInMainScope { sendProvidedLink() }
            }
        )

        teacherButton.addEventListener("click", {
                redirectToTeacherPage()
            }
        )
    }

    private suspend fun sendProvidedLink()
    {
        if (invitationLink.isEmpty())
        {
            invitationLinkPrompt.textContent = "Please provide an invitation link"
            invitationLinkField.style.backgroundColor = Color.STEVEN_ERROR.toString()
            return
        }

        // val response = get("https://s-friedl.dev.spsejecna.net/link")
        val response = get("http://localhost/link?eventCode=$invitationLink")

        if (response.ok)
        {
            redirectToTeacherPage()
        }
        else
        {
            console.log("Failed to send invitation link")
        }
    }

    private fun redirectToTeacherPage()
    {
        window.location.href += "/take-me-in"
    }
}
