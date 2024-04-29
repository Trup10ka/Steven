package me.trup10ka.shared.util

fun String.attachHeaderParam(paramName: String, param: String, attachAmpersand: Boolean = true): String
{
    if (this.contains("?"))
        return "$this${if (attachAmpersand) "&" else ""}$paramName$param"
    return "$this?$paramName$param"
}

infix fun String.withLocation(location: String): String
{
    return "$this${if (this.contains("/") || location[0] == '/') "" else "/"}$location"
}