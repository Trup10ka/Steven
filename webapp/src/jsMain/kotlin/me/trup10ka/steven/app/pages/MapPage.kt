package me.trup10ka.steven.app.pages

import kotlinx.browser.document
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import me.trup10ka.shared.data.EventMember
import me.trup10ka.shared.util.IdType.EVENT
import me.trup10ka.shared.util.IdType.MEMBER
import me.trup10ka.shared.util.idOf
import me.trup10ka.shared.util.withLocation
import me.trup10ka.steven.app.StevenClient
import me.trup10ka.steven.app.geo.GeoProvider
import me.trup10ka.steven.app.geo.JSPureGeoProvider
import me.trup10ka.steven.app.util.*

class MapPage(private val stevenClient: StevenClient) : Page
{
    private val membersContainer = getElementById("members-container")

    private val geoProvider: GeoProvider = JSPureGeoProvider()

    private val map = L.map("map")

    private val allMarkers = mutableMapOf<String, Marker>()

    private val allMembers = mutableListOf<EventMember>()

    private val memberId: String
        get() = getLastPathSegment() idOf MEMBER

    private val eventId: String
        get() = getLastPathSegment() idOf EVENT

    override fun setupPage()
    {
        geoProvider.sendLocation(memberId)
        setupMap()
        launchInMainScope { gatherAllLocations() }
    }

    private fun setupMap()
    {
        map.setView(arrayOf(51.50, -0.11), 13)

        val tileLayerOptions = createTileLayerOptions()

        L.tileLayer(
            "https://tile.openstreetmap.org/{z}/{x}/{y}.png",
            tileLayerOptions
        ).addTo(map)
    }

    private suspend fun gatherAllLocations()
    {
        val location = "/api/events/$eventId/members"

        val response = get("http://localhost:8000" withLocation location)

        if (!response.ok)
        {
            console.error("Failed to fetch locations")
            return
        }

        val members = Json.decodeFromString<Array<EventMember>>(
            response.json().await().toString()
        )

        allMembers.addAll(members)
        displayLocationsOnMap()
    }

    private fun displayLocationsOnMap()
    {
        allMembers.forEach { member ->

            if (member.lastLocation != null)
            {
                val locationArray = arrayOf(
                    member.lastLocation!!.latitude,
                    member.lastLocation!!.longitude
                )

                val marker = allMarkers.getOrPut(member.id) {

                    val marker = L.marker(
                        locationArray,
                        createMarkerTitleOption("${member.name} ${member.surnameInitial}")
                    )

                    marker.addTo(map)
                    marker
                }
                marker.setLatLng(locationArray)
            }
        }
    }
}
