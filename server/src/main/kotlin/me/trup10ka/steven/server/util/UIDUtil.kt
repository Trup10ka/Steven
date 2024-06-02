package me.trup10ka.steven.server.util

import kotlin.random.Random

private const val ALL_ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

/**
 * Generates a random number between 0 and the length of [all alphanumeric][ALL_ALPHANUMERIC] characters.
 *
 * Represents the index of a random alphanumeric character.
 */
private val randomAlphanumericCharIndex: Int
    get() = Random.nextInt(ALL_ALPHANUMERIC.length)

/**
 * Generates a random alphanumeric for a human.
 *
 * The UID is prefixed with "M" and has length 6.
 */
val humanUID: String
    get() = "M" + generateRandomAlphanumeric(6)

/**
 * Generates a random alphanumeric for an event.
 *
 * The UID is prefixed with "E" and has length 4.
 */
val eventUID: String
    get() = "E" + generateRandomAlphanumeric(4)

/**
 * Generates a UID as a combination of an event UID and a human UID.
 */
fun generateUID() = "${eventUID}-${humanUID}"


/**
 * Generates a random alphanumeric string of the given length.
 *
 * @param length the length of the string to generate
 */
private fun generateRandomAlphanumeric(length: Int): String
{
    return (1..length)
        .map { ALL_ALPHANUMERIC[randomAlphanumericCharIndex] }
        .joinToString("")
}
