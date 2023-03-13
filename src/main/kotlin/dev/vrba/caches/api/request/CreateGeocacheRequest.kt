package dev.vrba.caches.api.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length


data class CreateGeocacheRequest(
    @field:NotBlank
    @field:Length(max = 512)
    val title: String,

    @field:NotBlank
    @field:Length(max = 4096)
    val content: String,

    @field:NotBlank
    @field:Pattern(regexp = "^(traditional|mystery)$")
    val type: String,

    @field:NotBlank
    val hint: String,

    val latitude: Double,
    val longitude: Double,

    @field:Length(max = 4096)
    val question: String? = null,

    @field:Length(max = 32)
    val solution: String? = null,

    @field:Length(max = 128)
    val challenge: String? = null,

    val fakeLatitude: Double? = null,
    val fakeLongitude: Double? = null,
)