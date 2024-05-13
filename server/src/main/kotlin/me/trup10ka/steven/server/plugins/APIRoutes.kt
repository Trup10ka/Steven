package me.trup10ka.steven.server.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.trup10ka.shared.data.dto.EventDTO
import me.trup10ka.shared.data.event.EventMember
import me.trup10ka.shared.data.dto.EventMemberDTO
import me.trup10ka.shared.util.IdType.*
import me.trup10ka.shared.util.attachHeaderParam
import me.trup10ka.shared.util.idOf
import me.trup10ka.steven.server.event.EventManager
import me.trup10ka.steven.server.event.TeacherVanguard
import me.trup10ka.steven.server.util.humanUID

fun Route.createNewEvent(eventManager: EventManager, teacherVanguard: TeacherVanguard)
{
    post("/event/create") {

        call.respondRedirect("http://localhost:8000/take-me-in")

        val eventDto = call.receive<EventDTO>()

        val teacher = teacherVanguard.getTeacherById(eventDto.teacherId)
        val students = generateMembersFromData(eventDto.students)

        if (teacher == null)
        {
            call.respond(HttpStatusCode.NotFound, "Teacher not found")
            return@post
        }

        val event = eventManager.createEvent(
            teacher,
            students,
            eventDto.name,
            eventDto.notes,
            eventDto.startDate,
            eventDto.endDate
        )

        if (event == null)
            call.respond(HttpStatusCode.Conflict, "Event not created")
        else
        {
            val path = "/take-me-in".attachHeaderParam("eventId", event.id).attachHeaderParam("memberId", event.teacher.id)
            println(path)
            call.respondRedirect(
                "http://localhost:8000/take-me-in"
                    .attachHeaderParam("eventId", event.id)
                    .attachHeaderParam("memberId", event.teacher.id)
            )
        }
        
    }
}

fun Route.getAllMembersFromEvent(eventManager: EventManager)
{
    get("/event/{id}/members") {

        val idParam = call.parameters["id"]!!
        val event = eventManager.getEventById(idParam idOf EVENT)

        if (event == null)
        {
            call.respond(HttpStatusCode.NotFound, "Event not found")
            return@get
        }

        if (event.students.any { it.id == idParam idOf MEMBER } || event.teacher.id == idParam idOf MEMBER)
        {
            call.respond(listOf(event.teacher, event.students))
            return@get
        }
        call.respond(HttpStatusCode.Unauthorized, "You are not a member of this event")
    }
}

private fun generateMembersFromData(members: List<EventMemberDTO>): List<EventMember>
{
    val listOfMembers = mutableListOf<EventMember>()

    for (member in members)
    {
        var id = humanUID

        while (id in listOfMembers.map { it.id })
            id = humanUID

        listOfMembers.add(
            EventMember(
                id,
                member.name,
                member.surname,
                member.isTeacher,
                member.telNumber
            )
        )
    }
    return listOfMembers
}
