package pages

import kotlinx.browser.document
import kotlinx.browser.window
import me.trup10ka.shared.util.Color
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import util.get


object InvitePage
{
    private val invitationLinkField = document.getElementById("invitation-link-input") as HTMLInputElement

    private val invitationLinkLabel = document.getElementById("invitation-link-label") as HTMLInputElement

    private val takeMeInButton = document.getElementById("take-me-in-button") as HTMLButtonElement

    private val teacherButton = document.getElementById("are-you-a-teacher-button") as HTMLButtonElement

    private val invitationLink: String
        get() = invitationLinkField.value

    fun setUpButtons()
    {
        takeMeInButton.addEventListener("click", {
                suspend { sendProvidedLink() }
            }
        )

        teacherButton.addEventListener("click", {
                suspend { redirectToTeacherPage() }
            }
        )
    }

    private suspend fun sendProvidedLink()
    {
        if (invitationLink.isEmpty())
        {
            invitationLinkLabel.value = "Please provide an invitation link"
            invitationLinkLabel.style.backgroundColor = Color.STEVEN_ERROR.toString()
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
