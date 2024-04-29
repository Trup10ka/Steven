package me.trup10ka.steven.app.pages

import kotlinx.browser.document
import me.trup10ka.shared.util.Color
import me.trup10ka.shared.util.attachHeaderParam
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import me.trup10ka.steven.app.util.get
import me.trup10ka.steven.app.util.launchInMainScope
import me.trup10ka.shared.util.ParamNames.*
import me.trup10ka.shared.util.withLocation


class InvitePage : Page
{
    private val invitationLinkField = document.getElementById("invitation-link-input") as HTMLInputElement

    private val invitationLinkPrompt = document.getElementById("invitation-link-prompt") as HTMLElement

    private val takeMeInButton = document.getElementById("take-me-in-button") as HTMLButtonElement

    private val teacherIdInputField = document.getElementById("teacher-id-input") as HTMLInputElement

    private val teacherButton = document.getElementById("are-you-a-teacher-button") as HTMLButtonElement

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

    private suspend fun sendProvidedLink()
    {
        if (invitationLink.isEmpty())
        {
            invitationLinkPrompt.textContent = "Please provide an invitation link"
            invitationLinkField.style.backgroundColor = Color.STEVEN_ERROR.toString()
            return
        }

        val location = "/take-me-in".attachHeaderParam(LOCATION.paramName, invitationLink)

        get("http://localhost" withLocation location)
    }

    private suspend fun redirectToTeacherPage()
    {
        if (teacherId.isEmpty())
        {
            teacherIdInputField.placeholder = "Incorrect teacher ID"
            teacherIdInputField.style.backgroundColor = Color.STEVEN_ERROR.toString()
            return
        }

        val location = "/teacher".attachHeaderParam(TEACHER_ID.paramName, teacherId)

        get("http://localhost" withLocation location)
    }
}
