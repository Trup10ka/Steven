package me.trup10ka.shared.data

import kotlinx.serialization.Serializable

@Serializable
data class Location(val latitude: Double, val longitude: Double)
{
    override fun toString(): String
    {
        return "$latitude,$longitude"
    }

    companion object
    {
        fun fromString(locationString: String): Location
        {
            val (latitude, longitude) = locationString.split(",", limit = 1)
            return Location(latitude.toDouble(), longitude.toDouble())
        }
    }
}
