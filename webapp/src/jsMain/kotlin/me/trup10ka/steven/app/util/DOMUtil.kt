package me.trup10ka.steven.app.util

import kotlinx.browser.document
import kotlinx.browser.window
import me.trup10ka.shared.data.EventMember
import org.w3c.dom.Element

fun getLastPathSegment() = window.location.pathname.split("/").last()

fun getElementById(id: String) = document.getElementById(id)!!

fun createMemberContainer(member: EventMember): Element
{
    val memberDiv = createMemberContainer()
    val memberInfo = createMemberInfo()
    val lastSeen = createLastSeen()


    memberInfo.apply {
        appendChild(createMemberName(member))
        appendChild(createMemberSurnameInitial(member))
    }

    lastSeen.apply {
        appendChild(createLabel("Last seen:"))
        appendChild(createLastSeen(member))
    }

    memberDiv.apply {
        appendChild(memberInfo)
        appendChild(lastSeen)
    }

    return memberDiv
}

private fun createMemberContainer() = createDiv().apply { classList.add("member-container") }

private fun createMemberInfo() = createDiv().apply { classList.add("member-info") }

private fun createLastSeen() = createDiv().apply { classList.add("last-seen") }

private fun createMemberName(member: EventMember) = createLabel(member.name).apply { classList.add("member-name") }

private fun createMemberSurnameInitial(member: EventMember) = createLabel(member.surnameInitial).apply { classList.add("member-surname") }

private fun createLastSeen(eventMember: EventMember) = createLabel(eventMember.lastLocation?.toString() ?: "No location yet").apply { classList.add("member-last-seen") }

private fun createDiv() = document.createElement("div")

private fun createLabel(text: String = "") = document.createElement("label").apply { textContent = text }
