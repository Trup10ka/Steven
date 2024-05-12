package me.trup10ka.shared.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EventMemberDTO(
    val name: String,
    val surname: String,
    val isTeacher: Boolean,
    val telNumber: String? = null,
)
