package dev.vrba.caches.api.dto

import dev.vrba.caches.api.domain.Geocache

data class GeocacheDetailedDto(
    val id: Int,
    val type: String,
    val title: String,
    val content: String,
    val found: Boolean,
    val locked: Boolean,
    val coordinates: GeocacheCoordinatesDto,
    val fakeCoordinates: GeocacheCoordinatesDto?,
    val question: String?,
    val solution: String?
)

fun Geocache.toDetailedDto(): GeocacheDetailedDto {
    val coordinates = GeocacheCoordinatesDto(latitude, longitude)
    val fakeCoordinates =
        if (fakeLatitude != null && fakeLongitude != null) GeocacheCoordinatesDto(fakeLatitude, fakeLongitude)
        else null

    return GeocacheDetailedDto(
        id,
        type,
        title,
        content,
        found,
        locked,
        coordinates,
        fakeCoordinates,
        question,
        solution
    )
}
