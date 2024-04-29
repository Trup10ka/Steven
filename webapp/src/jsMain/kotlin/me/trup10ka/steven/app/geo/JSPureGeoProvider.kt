package me.trup10ka.steven.app.geo

import me.trup10ka.shared.data.Location
import me.trup10ka.steven.app.util.attachHeaderParam
import me.trup10ka.steven.app.util.get
import me.trup10ka.steven.app.util.launchInMainScope

class JSPureGeoProvider : GeoProvider
{
    override fun sendLocation()
    {
        callJsGetLocation { lat, long ->
            val location = Location(lat.toDouble(), long.toDouble())
            launchInMainScope {
                get(
                    "/api/location".attachHeaderParam(location.toString())
                )
            }
        }
    }
}

external fun callJsGetLocation(collectLocation: (Number, Number) -> Unit)
