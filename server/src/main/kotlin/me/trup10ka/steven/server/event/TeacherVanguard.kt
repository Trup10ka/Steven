package me.trup10ka.steven.server.event

import me.trup10ka.shared.data.event.EventMember

class TeacherVanguard
{

    private val teachers = mutableListOf<EventMember>()

    // TODO: REMOVE THIS
    init
    {
        teachers.add(EventMember("12345", "Steven", "Trup", true, "1"))
    }

    fun addTeacher(teacher: EventMember)
    {
        teachers.add(teacher)
    }

    fun isTeacher(id: String) = teachers.any { it.id == id && it.isTeacher }

    fun getTeacherById(id: String) = teachers.find { it.id == id }
}
