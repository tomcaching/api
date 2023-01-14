package dev.vrba.caches.api.controller

import dev.vrba.caches.api.dto.GeocacheDto
import dev.vrba.caches.api.dto.toDto
import dev.vrba.caches.api.request.CreateGeocacheRequest
import dev.vrba.caches.api.service.GeocachesService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/caches")
class GeocachesController(private val service: GeocachesService) {

    @GetMapping
    suspend fun listAll(): ResponseEntity<List<GeocacheDto>> {
        return service.findAll()
            .map { it.toDto() }
            .let { ResponseEntity.ok(it) }
    }

    @PostMapping("/create")
    suspend fun createGeocache(@Valid @RequestBody request: CreateGeocacheRequest): ResponseEntity<List<GeocacheDto>> {
        service.createGeocache(
            request.title,
            request.type,
            request.content,
            request.latitude,
            request.longitude,
            request.question,
            request.solution,
            request.fakeLatitude,
            request.fakeLongitude
        )

        return listAll()
    }
}