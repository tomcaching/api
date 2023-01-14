package dev.vrba.caches.api.controller

import dev.vrba.caches.api.dto.GeocacheDto
import dev.vrba.caches.api.dto.toDto
import dev.vrba.caches.api.service.GeocachesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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

}