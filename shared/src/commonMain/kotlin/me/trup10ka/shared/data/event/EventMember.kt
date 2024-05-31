package me.trup10ka.shared.data.event

import kotlinx.serialization.Serializable
import me.trup10ka.shared.data.Location

@Serializable
data class EventMember(
    val id: String,
    val name: String,
    val surname: String,
    val isTeacher: Boolean,
    val telNumber: String? = null,
    var lastLocation: Location? = null
)
{
    fun updateLocation(location: Location)
    {
        lastLocation = location
    }
}