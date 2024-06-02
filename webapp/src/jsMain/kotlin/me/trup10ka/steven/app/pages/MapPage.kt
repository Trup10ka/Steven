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

/**
 * Page for displaying a map with all members of an event.
 *
 * This page is used to display a map with all members of an event.
 */
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


    /**
     * During the setup phase of this page, firstly, the location of the current user is sent to the server.
     *
     * Then, the map is set up with the default view and the OpenStreetMap tile layer.
     *
     * After that, all locations of the members of the event are fetched from the server.
     */
    override fun setupPage()
    {
        geoProvider.sendLocation(memberId, eventId)
        setupMap()

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
        val response = get("/api/event/$eventId-$memberId/members")

        if (!response.ok)
        {
            console.error("Failed to fetch locations")
            return
        }

        val jsonObject = response.json().await()

        val members = Json.decodeFromString<Array<EventMember>>(JSON.stringify(jsonObject))

        allMembers.addAll(members)
    }

    private fun displayEveryoneInMemberBox()
    {
        membersContainer.innerHTML = ""

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
