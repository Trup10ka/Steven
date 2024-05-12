package me.trup10ka.shared.data.dto

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class EventDTO(
    val name: String,
    val notes: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val teacherId: String,
    val students: List<EventMemberDTO>
)
