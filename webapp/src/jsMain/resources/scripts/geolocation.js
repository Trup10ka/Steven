/**
 * JS function to get the current location of the user
 *
 * Is called from Kotlin code in {@link JSPureGeoProvider}
 * @param processLocation
 */
function callJsGetLocation(processLocation)
{
    navigator.geolocation.getCurrentPosition(
        (position) => {
            processLocation(position.coords.latitude, position.coords.longitude)
        },
        (error) => {
            console.error(error)
        },
        { enableHighAccuracy: true }
    )
}
