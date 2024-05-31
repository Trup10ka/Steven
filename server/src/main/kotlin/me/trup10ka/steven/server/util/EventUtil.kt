package me.trup10ka.steven.server.util


import me.trup10ka.shared.data.event.Event

fun checkIfMemberIsPartOfEvent(event: Event, memberId: String): Boolean
{
    return event.students.any { it.id == memberId } || event.teacher.id == memberId
}