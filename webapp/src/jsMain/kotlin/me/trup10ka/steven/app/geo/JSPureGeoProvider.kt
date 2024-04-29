package me.trup10ka.steven.app.geo

import me.trup10ka.shared.data.Location
import me.trup10ka.shared.util.attachHeaderParam
import me.trup10ka.steven.app.util.get
import me.trup10ka.steven.app.util.launchInMainScope

class JSPureGeoProvider : GeoProvider
{
    override fun getLocation(): Location?
    {
        var location: Location? = null
        callJsGetLocation { lat, long ->
            location = Location(lat.toDouble(), long.toDouble())
        }
        return location
    }
}

external fun callJsGetLocation(collectLocation: (Number, Number) -> Unit)
