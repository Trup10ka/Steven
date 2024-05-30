package me.trup10ka.steven.app.pages

import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import me.trup10ka.shared.data.event.EventMember
import me.trup10ka.shared.util.IdType.EVENT
import me.trup10ka.shared.util.IdType.MEMBER
import me.trup10ka.shared.util.idOf
import me.trup10ka.shared.util.withLocation
import me.trup10ka.steven.app.geo.GeoProvider
import me.trup10ka.steven.app.geo.JSPureGeoProvider
import me.trup10ka.steven.app.util.*

class MapPage : Page
{
    private val membersContainer = getElementById("members-container")

    private val geoProvider: GeoProvider = JSPureGeoProvider()

    private val map = L.map("map")

    private val allMarkers = mutableMapOf<String, Marker>()

    private val allMembers = mutableListOf<EventMember>()

    private val eventId: String
        get() = getLastPathSegment() idOf EVENT

    private val memberId: String
        get() = getLastPathSegment() idOf MEMBER


    override fun setupPage()
    {
        setupMap()
        geoProvider.sendLocation(memberId)

        launchInMainScope {
            gatherAllLocations()
            displayEveryoneInMemberBox()
            displayLocationsOnMap()
        }
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
        val location = "/api/event/$eventId-$memberId/members"

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
    }

    private fun displayEveryoneInMemberBox()
    {
        allMembers.forEach { member ->

            val memberContainer = createMemberOnMapPageContainer(member)

            membersContainer.appendChild(memberContainer)
        }
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

                val marker = allMarkers.getOrPut(member.id) { createNewMarker(locationArray, member) }
                marker.setLatLng(locationArray)
            }
        }
    }

    private fun createNewMarker(locationArray: Array<Double>, member: EventMember): Marker
    {
        val marker = L.marker(
            locationArray,
            createMarkerTitleOption("${member.name} ${member.surname}")
        )

        marker.addTo(map)

        return marker
    }
}
