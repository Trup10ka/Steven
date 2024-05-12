package me.trup10ka.steven.app.util

import kotlinx.browser.window
import kotlinx.coroutines.*
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import kotlin.js.json

suspend fun fetch(method: String, url: String, body: dynamic = null): Response =
    window.fetch(
        url, RequestInit(
            method = method,
            body = body,
            headers = json(
                "Content-Type" to "application/json",
                "Accept" to "application/json",
                "pragma" to "no-cache"
            )
        )
    ).await()


suspend fun get(url: String): Response =
    fetch("GET", url)

suspend fun post(url: String, body: String): Response =
    fetch("POST", url, body)
