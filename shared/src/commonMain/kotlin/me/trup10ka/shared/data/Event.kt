package me.trup10ka.shared.data

import kotlinx.datetime.LocalDate

data class Event(
    val id: String,
    val name: String,
    val notes: String?,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val teacher: EventMember,
    val students: List<EventMember>
)