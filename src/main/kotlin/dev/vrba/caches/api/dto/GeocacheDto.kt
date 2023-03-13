package dev.vrba.caches.api.dto

import dev.vrba.caches.api.domain.Geocache


data class GeocacheCoordinatesDto (
    val lat: Double,
    val lng: Double
)

data class GeocacheDto(
    val id: Int,
    val type: String,
    val found: Boolean,
    val title: String,
    val content: String,
    val hint: String,
    val coordinates: GeocacheCoordinatesDto,
    val locked: Boolean,
    val question: String?,
    val challenge: String?
)

fun Geocache.toDto(): GeocacheDto {
    val coordinates =
        if (locked && fakeLatitude != null && fakeLongitude != null) GeocacheCoordinatesDto(fakeLatitude, fakeLongitude)
        else GeocacheCoordinatesDto(latitude, longitude)

    val hint = if (locked) "Hint se zpřístupní až po vyřešení úkolu" else hint

    return GeocacheDto(
        id,
        type,
        found,
        title,
        content,
        hint,
        coordinates,
        locked,
        question,
        challenge
    )
}