function callJsGetLocation(processLocation)
{
    navigator.geolocation.getCurrentPosition(
        (position) => {
            processLocation(position.coords.latitude, position.coords.longitude)
        },
        (error) => {
            console.error(error)
        }
    )
}
