package me.trup10ka.steven.app.geo

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.trup10ka.shared.data.Location
import me.trup10ka.shared.util.withLocation
import me.trup10ka.steven.app.util.launchInMainScope
import me.trup10ka.steven.app.util.post


class JSPureGeoProvider : GeoProvider
{
    override fun sendLocation(senderId: String, eventId: String)
    {
        callJsGetLocation { latitude, longitude ->

            val location = Location(latitude.toDouble(), longitude.toDouble())

            launchInMainScope {
                post("/api/event/$eventId/location/" withLocation senderId,
                    Json.encodeToString(location))
            }
        }
    }
}

external fun callJsGetLocation(collectLocation: (Number, Number) -> Unit)
