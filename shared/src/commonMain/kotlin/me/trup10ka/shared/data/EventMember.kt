package me.trup10ka.shared.data

data class EventMember(
    val id: String,
    val name: String,
    val surnameInitial: String,
    val isTeacher: Boolean,
    val telNumber: String?
)
