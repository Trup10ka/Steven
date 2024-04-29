package me.trup10ka.steven.app.geo

import me.trup10ka.shared.data.Location

interface GeoProvider
{
    fun getLocation(): Location?
}
