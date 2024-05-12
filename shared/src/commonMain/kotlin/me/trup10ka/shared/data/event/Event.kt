package me.trup10ka.shared.data.event

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: String,
    val name: String,
    val notes: String?,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val teacher: EventMember,
    val students: List<EventMember>
)
