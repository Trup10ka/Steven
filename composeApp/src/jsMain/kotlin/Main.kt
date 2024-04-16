import kotlinx.browser.document
import pages.InvitePage

fun main()
{
    document.addEventListener(
        "DOMContentLoaded", { InvitePage.setUpButtons() }
    )
}
