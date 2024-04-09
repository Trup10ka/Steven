class JSPlatform : Platform {
    override val name: String = "Kotlin/JS"
}

actual fun getPlatform(): Platform
{
    return JSPlatform()
}
