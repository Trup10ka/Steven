package me.trup10ka.steven.app.geo

import me.trup10ka.shared.data.Location

class JSPureGeoProvider : GeoProvider
{
    override fun sendLocation()
    {
        callJsGetLocation { lat, long ->
            val location = Location(lat.toDouble(), long.toDouble())
            console.log("Location: $location")
        }
    }
}

external fun callJsGetLocation(collectLocation: (Number, Number) -> Unit)
