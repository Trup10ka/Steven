package me.trup10ka.steven.server.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.trup10ka.shared.data.Location
import me.trup10ka.shared.data.dto.EventDTO
import me.trup10ka.shared.data.event.EventMember
import me.trup10ka.shared.data.dto.EventMemberDTO
import me.trup10ka.shared.data.event.Event
import me.trup10ka.shared.util.IdType.*
import me.trup10ka.shared.util.attachHeaderParam
import me.trup10ka.shared.util.idOf
import me.trup10ka.steven.server.event.EventManager
import me.trup10ka.steven.server.event.TeacherVanguard
import me.trup10ka.steven.server.util.checkIfMemberIsPartOfEvent
import me.trup10ka.steven.server.util.humanUID

fun Route.createNewEvent(eventManager: EventManager, teacherVanguard: TeacherVanguard)
{
    post("/event/create") {

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
            call.respond(HttpStatusCode.OK, event.id)
    }
}

fun Route.receiveClientLocation(eventManager: EventManager)
{
    post("/event/{eventId}/location/{memberId}") {

        val eventId = call.parameters["eventId"]!!
        val memberId = call.parameters["memberId"]!!

        val location = call.receive<Location>()

        val event = eventManager.getEventById(eventId)

        if (event == null)
        {
            call.respond(HttpStatusCode.NotFound, "Event not found")
            return@post
        }

        if (!checkIfMemberIsPartOfEvent(event, memberId))
        {
            call.respond(HttpStatusCode.Unauthorized, "You are not a member of this event")
            return@post
        }

        event.updateMemberLocation(memberId, location)
        call.respond(HttpStatusCode.OK)
    }
}

fun Route.getAllMembersFromEvent(eventManager: EventManager)
{
    get("/event/{whole-id}/members") {

        val idParam = call.parameters["whole-id"]!!
        val event = eventManager.getEventById(idParam idOf EVENT)

        if (event == null)
        {
            call.respond(HttpStatusCode.NotFound, "Event not found")
            return@get
        }

        if (checkIfMemberIsPartOfEvent(event, idParam idOf MEMBER))
        {
            val allMembers = listOf(event.teacher) + event.students
            call.respond(allMembers)
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
