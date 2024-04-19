package me.trup10ka.steven.server.event

import kotlinx.datetime.LocalDate
import me.trup10ka.shared.data.Event
import me.trup10ka.shared.data.EventMember
import me.trup10ka.steven.server.util.eventUID

class EventManager(private val teacherVanguard: TeacherVanguard)
{
    private val allEvents = mutableListOf<Event>()

    fun createEvent(teacher: EventMember, students: List<EventMember>, name: String, notes: String? = null, startDate: LocalDate, endDate: LocalDate): Boolean
    {
        if (!teacherVanguard.isTeacher(teacher.id)) return false

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
        return true
    }

    fun getEventById(id: String) = allEvents.find { it.id == id }
}