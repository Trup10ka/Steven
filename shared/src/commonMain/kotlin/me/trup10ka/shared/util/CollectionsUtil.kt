package me.trup10ka.shared.util

fun <T> List<T>.second(): T
{
    if (size < 2)
        throw NoSuchElementException("List doesn't have a second element")

    return this[1]
}
