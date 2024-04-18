package me.trup10ka.steven.server.util

import kotlin.random.Random

private const val ALL_ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

private val randomAlphanumericCharIndex: Int
    get() = Random.nextInt(ALL_ALPHANUMERIC.length)

val humanUUID: String
    get() = generateRandomAlphanumeric(7)

val eventUUID: String
    get() = generateRandomAlphanumeric(5)

fun generateUUID() = "${eventUUID}-${humanUUID}"

private fun generateRandomAlphanumeric(length: Int): String
{
    return (1..length)
        .map { ALL_ALPHANUMERIC[randomAlphanumericCharIndex] }
        .joinToString("")
}
