package util

import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import kotlin.js.Promise
import kotlin.js.json

private suspend fun Promise<Response>.assertStatus() = await().apply {
    status.toInt().also {
        check(200 == it || 0 == it) {
            "Operation not code 200: $status  $url".also { msg ->
                console.log(msg)
                window.alert(msg)
            }
        }
    }
}

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
    ).assertStatus()


private suspend fun get(url: String): Response =
    fetch("GET", url)

private suspend fun post(url: String, body: dynamic): Response =
    fetch("POST", url, JSON.stringify(body))
