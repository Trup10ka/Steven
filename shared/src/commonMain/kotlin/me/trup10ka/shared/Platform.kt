package me.trup10ka.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
