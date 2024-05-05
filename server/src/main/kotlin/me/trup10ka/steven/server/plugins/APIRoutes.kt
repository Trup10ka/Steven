package me.trup10ka.steven.server.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.trup10ka.shared.data.Event
import me.trup10ka.shared.util.IdType.*
import me.trup10ka.shared.util.attachHeaderParam
import me.trup10ka.shared.util.idOf
import me.trup10ka.steven.server.event.EventManager

fun Route.createNewEvent(eventManager: EventManager)
{
    post("/event/create") {

        val eventDto = call.receive<Event>()

        val event = eventManager.createEvent(
            eventDto.teacher,
            eventDto.students,
            eventDto.name,
            eventDto.notes,
            eventDto.startDate,
            eventDto.endDate
        )

        if (event == null)
            call.respond(HttpStatusCode.Conflict, "Event not created")
        else
            call.respondRedirect("/take-me-in".attachHeaderParam("id", event.id))
    }
}

fun Route.getAllMembersFromEvent(eventManager: EventManager)
{
    get("/event/{id}/members") {

        val idParam = call.parameters["id"]!!
        val event = eventManager.getEventById(idParam)

        if (event == null)
        {
            call.respond(HttpStatusCode.NotFound, "Event not found")
            return@get
        }

        if (event.students.any { it.id == idParam idOf MEMBER })
        {
            call.respond(event.students)
            return@get
        }
        call.respond(HttpStatusCode.Unauthorized, "You are not a member of this event")
    }
}
