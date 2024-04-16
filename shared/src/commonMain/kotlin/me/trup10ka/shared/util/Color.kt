package me.trup10ka.shared.util

class Color(
    private val r: Int,
    private val g: Int,
    private val b: Int,
    private val alpha: Float = 1f
)
{
    override fun toString() = "rgb($r, $g, $b, $alpha)"

    companion object
    {
        val STEVEN_PRIMARY = fromRGB(58, 41, 126)
        val STEVEN_SECONDARY = fromRGB(111, 55, 166)
        val STEVEN_THIRDED = fromRGB(20, 8, 64)

        val STEVEN_ERROR = fromRGB(190, 10, 20, 0.50f)

        private fun fromRGB(r: Int, g: Int, b: Int, alpha: Float = 1f): Color
        {
            return Color(r, g, b, alpha)
        }
    }
}
