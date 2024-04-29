package me.trup10ka.steven.app.pages

import me.trup10ka.steven.app.StevenClient
import me.trup10ka.steven.app.geo.GeoProvider
import me.trup10ka.steven.app.geo.JSPureGeoProvider
import me.trup10ka.steven.app.util.L
import me.trup10ka.steven.app.util.TileLayerOptions

class MapPage(private val stevenClient: StevenClient) : Page
{
    private val geoProvider: GeoProvider = JSPureGeoProvider()

    private val map = L.map("map")

    override fun setupPage()
    {
        geoProvider.sendLocation(stevenClient.memberId)
        setupMap()
        gatherAllLocations()
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

    private fun gatherAllLocations()
    {

    }

    private fun createTileLayerOptions(): TileLayerOptions
    {
        val defaultTileOptions = object {
            val minZoom = 1
            val maxZoom = 19
            val attribution =
                "Map data &copy; <a href=\"https://www.openstreetmap.org/\">OpenStreetMap</a> contributors, <a href=\"https://creativecommons.org/licenses/by-sa/2.0/\">CC-BY-SA</a>"
        }

        return defaultTileOptions.unsafeCast<TileLayerOptions>()
    }
}
