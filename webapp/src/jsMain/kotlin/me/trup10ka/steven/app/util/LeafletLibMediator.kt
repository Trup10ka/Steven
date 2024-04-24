package me.trup10ka.steven.app.util

external object L
{
    fun map(id: String): Map
    fun tileLayer(url: String, options: TileLayerOptions): TileLayer
    fun marker(latlng: Array<Double>, options: MarkerOptions = definedExternally): Marker
    fun icon(options: IconOptions): Icon
}

external class Map
{
    fun setView(center: Array<Double>, zoom: Int): Map
}

external interface Marker : Layer
{
    fun addTo(map: Map)
    fun setLatLng(latlng: Array<Double>)
}

external interface Icon

external interface TileLayer
{
    fun addTo(map: Map)
}

external interface Layer
{
    var attribution: String
}

external class TileLayerOptions : Layer
{
    var minZoom: Int
    var maxZoom: Int
    var subdomains: dynamic
    var errorTileUrl: String
    var zoomOffset: Int
    var tms: Boolean
    var zoomReverse: Boolean
    var detectRetina: Boolean
    var crossOrigin: dynamic
    var referrerPolicy: dynamic
    override var attribution: String
}

external interface MarkerOptions
{
    var icon: Icon
    var keyboard: Boolean
    var title: String
    var alt: String
    var zIndexOffset: Int
    var opacity: Float
    var riseOnHover: Boolean
    var riseOffset: Int
    var pane: String
    var shadowPane: String
    var bubblingMouseEvents: Boolean
    var autoPanOnFocus: Boolean
}

external interface IconOptions
{
    var iconUrl: String
    var iconRetinaUrl: String
    var iconSize: Array<Int>
    var iconAnchor: Array<Int>
    var popupAnchor: Array<Int>
    var tooltipAnchor: Array<Int>
    var shadowUrl: String
    var shadowRetinaUrl: String
    var shadowSize: Array<Int>
    var shadowAnchor: Array<Int>
    var className: String
    var crossOrigin: dynamic
}

external class LatLng
{
    var lat: Double
    var lng: Double
}
