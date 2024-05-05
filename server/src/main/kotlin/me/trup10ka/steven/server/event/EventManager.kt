package me.trup10ka.steven.server.event

import kotlinx.datetime.LocalDate
import me.trup10ka.shared.data.Event
import me.trup10ka.shared.data.EventMember
import me.trup10ka.steven.server.util.eventUID

class EventManager(private val teacherVanguard: TeacherVanguard)
{
    private val allEvents = mutableListOf<Event>()

    fun createEvent(teacher: EventMember, students: List<EventMember>, name: String, notes: String? = null, startDate: LocalDate, endDate: LocalDate): Event?
    {
        if (!teacherVanguard.isTeacher(teacher.id)) return null

        if (startDate > endDate) return null

        val event = Event(
            eventUID,
            name,
            notes,
            startDate,
            endDate,
            teacher,
            students
        )
        allEvents.add(event)
        return event
    }

    fun removeEvent(id: String): Boolean
    {
        val event = allEvents.find { it.id == id } ?: return false
        allEvents.remove(event)
        return true
    }

    fun getEventById(id: String) = allEvents.find { it.id == id }
}
