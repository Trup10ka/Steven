package me.trup10ka.steven.server.event

import kotlinx.datetime.LocalDate
import me.trup10ka.shared.data.event.Event
import me.trup10ka.shared.data.event.EventMember
import me.trup10ka.steven.server.util.eventUID

class EventManager(private val teacherVanguard: TeacherVanguard)
{
    private val allEvents = mutableListOf<Event>()

    // TODO: REMOVE THIS
    init
    {
        val teacher = teacherVanguard.getTeacherById("12345")!!
        val student1 = EventMember("2", "John", "Doe", false)
        val student2 = EventMember("3", "Jane", "Doe", false)
        val event = Event(
            "12345",
            "Test Event",
            "This is a test event",
            LocalDate(2021, 10, 1),
            LocalDate(2021, 10, 5),
            teacher,
            listOf(student1, student2)
        )
        allEvents.add(event)
    }

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
