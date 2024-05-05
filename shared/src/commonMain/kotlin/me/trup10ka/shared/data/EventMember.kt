package me.trup10ka.shared.data

import kotlinx.serialization.Serializable

@Serializable
data class EventMember(
    val id: String,
    val name: String,
    val surname: String,
    val isTeacher: Boolean,
    val telNumber: String?,
    val lastLocation: Location? = null
)
