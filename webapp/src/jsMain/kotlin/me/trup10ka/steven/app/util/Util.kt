package me.trup10ka.steven.app.util

import kotlinx.browser.window

fun getLastPathSegment() = window.location.pathname.split("/").last()