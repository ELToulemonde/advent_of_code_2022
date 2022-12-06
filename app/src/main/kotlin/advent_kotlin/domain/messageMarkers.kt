package advent_kotlin.domain

fun getMarkerIndex(message: String, numberOfRequiredCharMarkers: Int): Int {
    var markerFound = false
    var index = numberOfRequiredCharMarkers - 1
    while (!markerFound) {
        index += 1
        if (message.subSequence((index - numberOfRequiredCharMarkers), index).chunked(1)
                .toSet().size == numberOfRequiredCharMarkers
        ) {
            markerFound = true
        }
    }
    return index
}