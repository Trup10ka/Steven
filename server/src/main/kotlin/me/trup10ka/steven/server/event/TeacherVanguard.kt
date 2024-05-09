package me.trup10ka.steven.server.event

import me.trup10ka.shared.data.EventMember

class TeacherVanguard
{

    private val teachers = mutableListOf<EventMember>()

    // TODO: REMOVE THIS
    init
    {
        teachers.add(EventMember("12345", "1", "1", true, "1"))
    }

    fun addTeacher(teacher: EventMember)
    {
        teachers.add(teacher)
    }

    fun isTeacher(id: String) = teachers.any { it.id == id && it.isTeacher }

    fun getTeacherById(id: String) = teachers.find { it.id == id }
}
