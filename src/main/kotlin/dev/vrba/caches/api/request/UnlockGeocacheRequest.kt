package dev.vrba.caches.api.request

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class UnlockGeocacheRequest(
    @field:NotBlank
    @field:Length(max = 32)
    val solution: String
)
