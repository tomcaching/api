package dev.vrba.caches.api.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("caches")
data class Geocache(
    @Id
    @Column("id")
    val id: Int,

    @Column("cache_type")
    val type: String,

    @Column("cache_title")
    val title: String,

    @Column("cache_content")
    val content: String,

    @Column("cache_hint")
    val hint: String,

    @Column("cache_found")
    val found: Boolean,

    @Column("cache_locked")
    val locked: Boolean,

    @Column("coordinates_latitude")
    val latitude: Double,

    @Column("coordinates_longitude")
    val longitude: Double,

    @Column("challenge_url")
    val challenge: String? = null,

    @Column("cache_question")
    val question: String? = null,

    @Column("cache_solution")
    val solution: String? = null,

    @Column("fake_coordinates_latitude")
    val fakeLatitude: Double? = null,

    @Column("fake_coordinates_longitude")
    val fakeLongitude: Double? = null
)