package me.trup10ka.steven.app.util

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.datetime.LocalDate
import me.trup10ka.shared.data.event.EventMember
import org.w3c.dom.*

fun getLastPathSegment() = window.location.pathname.split("/").last()

fun getElementById(id: String) = document.getElementById(id)!!

fun createEventCreatingMemberContainer(name: String, surname: String) =
    createDiv().apply outerApply@{

        classList.add("event-member-added")

        val removeButton = createButton("-").apply {
            classList.add("remove-member-button")
            addEventListener("click", { this@outerApply.remove() })
        }

        appendChildren(

            createLabel(name).apply {
                classList.add("member-name")
            },
            createLabel(surname).apply {
                classList.add("member-surname")
            },
            removeButton
        )
    }



fun createMemberOnMapPageContainer(member: EventMember): Element
{
    val memberDiv = createMemberOnMapPageContainer()
    val memberInfo = createMemberInfo()
    val lastSeen = createLastSeen()


    memberInfo.apply {
        appendChild(createMemberName(member))
        appendChild(createMemberSurnameInitial(member))
    }

    lastSeen.apply {
        appendChild(createLabel("Last seen: "))
        appendChild(createLastSeen(member))
    }

    memberDiv.apply {
        appendChild(memberInfo)
        appendChild(lastSeen)
    }

    return memberDiv
}

private fun createMemberOnMapPageContainer() = createDiv().apply { classList.add("member-container") }

private fun createMemberInfo() = createDiv().apply { classList.add("member-info") }

private fun createLastSeen() = createDiv().apply { classList.add("last-seen") }

private fun createMemberName(member: EventMember) = createLabel(member.name).apply { classList.add("member-name") }

private fun createMemberSurnameInitial(member: EventMember) = createLabel(member.surname).apply { classList.add("member-surname") }

private fun createLastSeen(eventMember: EventMember) = createLabel(eventMember.lastLocation?.toString() ?: "No location yet").apply { classList.add("member-last-seen") }

fun createDiv() = document.createElement("div") as HTMLDivElement

fun createLabel(text: String = "") = document.createElement("label").apply { textContent = text } as HTMLLabelElement

fun createButton(text: String = "Button") = document.createElement("button").apply { textContent = text } as HTMLButtonElement

fun Node.appendChildren(vararg children: Node) = children.forEach { appendChild(it) }
