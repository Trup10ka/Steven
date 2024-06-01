package me.trup10ka.steven.app.pages

import me.trup10ka.shared.util.Color
import me.trup10ka.shared.util.attachHeaderParam
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import me.trup10ka.steven.app.util.get
import me.trup10ka.steven.app.util.launchInMainScope
import me.trup10ka.shared.util.ParamNames.*
import me.trup10ka.steven.app.util.getElementById


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

    private suspend fun sendProvidedLink()
    {
        if (invitationLink.isEmpty())
        {
            invitationLinkPrompt.textContent = "Please provide an invitation link"
            invitationLinkField.style.backgroundColor = Color.STEVEN_ERROR.toString()
            return
        }

        get("/take-me-in".attachHeaderParam(LOCATION.paramName, invitationLink))
    }

    private suspend fun redirectToTeacherPage()
    {
        if (teacherId.isEmpty())
        {
            teacherIdInputField.placeholder = "Incorrect teacher ID"
            teacherIdInputField.style.backgroundColor = Color.STEVEN_ERROR.toString()
            return
        }

        get("/teacher".attachHeaderParam(TEACHER_ID.paramName, teacherId))
    }
}
