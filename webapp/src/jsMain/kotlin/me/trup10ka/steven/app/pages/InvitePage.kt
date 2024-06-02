package me.trup10ka.steven.app.pages

import kotlinx.browser.window
import me.trup10ka.shared.util.Color
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import me.trup10ka.steven.app.util.get
import me.trup10ka.steven.app.util.launchInMainScope
import me.trup10ka.shared.util.withLocation
import me.trup10ka.steven.app.util.getElementById

/**
 * This page is responsible for handling the invitation link and teacher ID input fields.
 *
 * @see Page
 */
class InvitePage : Page
{
    private val invitationLinkField = getElementById("invitation-link-input") as HTMLInputElement

    private val invitationLinkPrompt = getElementById("invitation-link-prompt") as HTMLElement

    private val takeMeInButton = getElementById("take-me-in-button") as HTMLButtonElement

    private val teacherIdInputField = getElementById("teacher-id-input") as HTMLInputElement

    private val teacherButton = getElementById("are-you-a-teacher-button") as HTMLButtonElement

    private val invitationLink: String
        get() = invitationLinkField.value

    private val teacherId: String
        get() = teacherIdInputField.value

    override fun setupPage()
    {
        setUpButtons()
    }

    private fun setUpButtons()
    {
        takeMeInButton.addEventListener("click", {
                launchInMainScope { sendProvidedLink() }
            }
        )

        teacherButton.addEventListener("click", {
                launchInMainScope { redirectToTeacherPage() }
            }
        )
    }

    /**
     * Sends id of desired event to the server and redirects to the event page if the event exists.
     *
     * If the event does not exist, an alert is shown and text field is highlighted with red color
     */
    private suspend fun sendProvidedLink()
    {
        if (invitationLink.isEmpty())
        {
            invitationLinkPrompt.textContent = "Please provide an invitation link"
            invitationLinkField.style.backgroundColor = Color.STEVEN_ERROR.toString()
            return
        }

        val response = get("/take-me-in" withLocation invitationLink)
        if (response.ok)
            window.location.href = "/take-me-in" withLocation invitationLink
        else
            window.alert("Event not found")
    }

    /**
     * Sends teacher ID to the server and redirects to the teacher page if the teacher exists.
     *
     * If the teacher does not exist, an alert is shown and text field is highlighted with red color
     */
    private suspend fun redirectToTeacherPage()
    {
        if (teacherId.isEmpty())
        {
            teacherIdInputField.placeholder = "Incorrect teacher ID"
            teacherIdInputField.style.backgroundColor = Color.STEVEN_ERROR.toString()
            return
        }

        val response = get("/are-you-a-teacher" withLocation teacherId)

        if (response.ok)
            window.location.href = "/are-you-a-teacher" withLocation teacherId
        else
            window.alert("You are not a teacher")
    }
}
