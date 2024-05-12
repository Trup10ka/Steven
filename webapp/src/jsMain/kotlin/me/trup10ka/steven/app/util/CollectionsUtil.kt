package me.trup10ka.steven.app.util

fun <T> Collection<T>.isNotEmpty(callback: () -> Unit): Boolean
{
    if (isEmpty())
    {
        callback()
        return false
    }
    return true
}
