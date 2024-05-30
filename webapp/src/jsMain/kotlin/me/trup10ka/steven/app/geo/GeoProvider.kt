package me.trup10ka.steven.app.geo


interface GeoProvider
{
    fun sendLocation(senderId: String, eventId: String)
}
