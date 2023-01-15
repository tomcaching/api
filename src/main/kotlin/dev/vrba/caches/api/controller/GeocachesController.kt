package dev.vrba.caches.api.controller

import dev.vrba.caches.api.dto.GeocacheDetailedDto
import dev.vrba.caches.api.dto.GeocacheDto
import dev.vrba.caches.api.dto.toDetailedDto
import dev.vrba.caches.api.dto.toDto
import dev.vrba.caches.api.request.CreateGeocacheRequest
import dev.vrba.caches.api.request.UnlockGeocacheRequest
import dev.vrba.caches.api.service.GeocachesService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/caches")
@CrossOrigin(origins = ["*"])
class GeocachesController(private val service: GeocachesService) {

    @GetMapping
    suspend fun listAll(): ResponseEntity<List<GeocacheDto>> {
        return service.findAll()
            .map { it.toDto() }
            .let { ResponseEntity.ok(it) }
    }

    @PostMapping("/unlock/{id}")
    suspend fun unlock(@PathVariable id: Int, @Valid @RequestBody request: UnlockGeocacheRequest): ResponseEntity<List<GeocacheDto>> {
        service.unlockGeocache(id, request.solution)

        return listAll()
    }

    @GetMapping("/detailed")
    suspend fun listAllDetailed(): ResponseEntity<List<GeocacheDetailedDto>> {
        return service.findAll()
            .map { it.toDetailedDto() }
            .let { ResponseEntity.ok(it) }
    }

    @PostMapping("/create")
    suspend fun createGeocache(@Valid @RequestBody request: CreateGeocacheRequest): ResponseEntity<List<GeocacheDetailedDto>> {
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

        return listAllDetailed()
    }

    @PostMapping("/update/{id}")
    suspend fun createGeocache(@PathVariable id: Int, @Valid @RequestBody request: CreateGeocacheRequest): ResponseEntity<List<GeocacheDetailedDto>> {
        service.updateGeocache(
            id,
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

        return listAllDetailed()
    }

    @PostMapping("/delete/{id}")
    suspend fun deleteGeocache(@PathVariable id: Int): ResponseEntity<List<GeocacheDetailedDto>> {
        service.deleteGeocache(id)

        return listAllDetailed()
    }
}