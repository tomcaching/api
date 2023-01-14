package dev.vrba.caches.api.service

import dev.vrba.caches.api.domain.Geocache
import dev.vrba.caches.api.repository.GeocacheRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class GeocachesService(private val repository: GeocacheRepository) {

    suspend fun findAll(): List<Geocache> {
        return withContext(Dispatchers.IO) { repository.findAll() }.toList()
    }

}