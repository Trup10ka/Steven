package me.trup10ka.steven.app.pages

import kotlinx.browser.window
import me.trup10ka.shared.util.Color
import me.trup10ka.steven.app.util.createDiv
import me.trup10ka.steven.app.util.createEventCreatingMemberContainer
import me.trup10ka.steven.app.util.getElementById
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement

class CreateEventPage : Page
{

    private val allMembersContainer = getElementById("all-members") as HTMLDivElement

    private val createEventButton = getElementById("create-event-button") as HTMLButtonElement

    private val addMemberButton = getElementById("add-member-button") as HTMLButtonElement

    private val memberNameInput = getElementById("member-name-input") as HTMLInputElement

    private val memberSurnameInput = getElementById("member-surname-input") as HTMLInputElement

    private val eventNameInput = getElementById("name-input") as HTMLInputElement

    private val startDateInput = getElementById("start-date-input") as HTMLInputElement

    private val endDateInput = getElementById("end-date-input") as HTMLInputElement

    private val eventNoteInput = getElementById("note-input") as HTMLInputElement


    override fun setupPage()
    {
        setupButtons()
    }

    private fun setupButtons()
    {
        createEventButton.addEventListener("click", {
                if (!checkInputs(eventNameInput, startDateInput, endDateInput))
                    return@addEventListener
            }
        )

        addMemberButton.addEventListener("click", {
                if (!checkInputs(memberNameInput, memberSurnameInput))
                    return@addEventListener
                addMember()
            }
        )
    }

    private fun addMember()
    {
        val memberName = memberNameInput.value
        val memberSurname = memberSurnameInput.value

        val memberDiv = createEventCreatingMemberContainer(memberName, memberSurname)
        allMembersContainer.appendChild(memberDiv)
    }

    private suspend fun sendEventInformation()
    {
        // Send event information to the server
    }

    private fun checkInputs(vararg inputs: HTMLInputElement): Boolean
    {
        var mandatoryFieldsFilled = true
        val listOfMissingInputs = mutableListOf<String>()

        for (input in inputs)
            if (input.value.isEmpty())
            {
                input.style.backgroundColor = Color.STEVEN_ERROR.toString()
                listOfMissingInputs.add(input.placeholder)
                mandatoryFieldsFilled = false
            }

        if (listOfMissingInputs.isNotEmpty())
        {
            window.alert("The following fields are missing: ${listOfMissingInputs.joinToString(", ")}")
        }

        return mandatoryFieldsFilled
    }

}
