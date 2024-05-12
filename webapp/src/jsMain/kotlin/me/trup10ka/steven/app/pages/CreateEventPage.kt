package me.trup10ka.steven.app.pages

import kotlinx.browser.window
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.trup10ka.shared.data.dto.EventDTO
import me.trup10ka.shared.data.dto.EventMemberDTO
import me.trup10ka.shared.util.Color
import me.trup10ka.steven.app.util.*
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

    private val addedMembers = mutableListOf<EventMemberDTO>()

    private val eventName: String
        get() = eventNameInput.value

    private val startDate: String
        get() = startDateInput.value

    private val endDate: String
        get() = endDateInput.value

    private val eventNote: String
        get() = eventNoteInput.value

    private val memberName: String
        get() = memberNameInput.value

    private val memberSurname: String
        get() = memberSurnameInput.value

    private lateinit var teacherId: String

    override fun setupPage()
    {
        teacherId = getLastPathSegment()
        setupButtons()
    }

    private fun setupButtons()
    {
        createEventButton.addEventListener("click", {
                if (
                    !checkInputs(eventNameInput, startDateInput, endDateInput) &&
                    addedMembers.isNotEmpty { window.alert("No members are added!") }
                    )
                    return@addEventListener

                launchInMainScope { sendEventInformation() }
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
        val memberDiv = createMemberContainerDiv()

        allMembersContainer.appendChild(memberDiv)

        addedMembers.add(
            EventMemberDTO(
                name = memberName,
                surname = memberSurname,
                false,
            )
        )
    }

    private fun createMemberContainerDiv(): HTMLDivElement
    {
        val memberName = memberName
        val memberSurname = memberSurname

        return createEventCreatingMemberContainer(memberName, memberSurname)
    }

    private suspend fun sendEventInformation()
    {
        val eventDTO = EventDTO(
            eventName,
            eventNote,
            LocalDate.parse(startDate),
            LocalDate.parse(endDate),
            teacherId,
            addedMembers
        )

        val body = Json.encodeToString(eventDTO);
        post("/api/event/create", body)
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
