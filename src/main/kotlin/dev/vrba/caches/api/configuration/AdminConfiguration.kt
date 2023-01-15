package dev.vrba.caches.api.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties("admin")
data class AdminConfiguration @ConstructorBinding constructor(
    val password: String
)