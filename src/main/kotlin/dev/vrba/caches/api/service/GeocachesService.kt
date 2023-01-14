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

    suspend fun createGeocache(
        title: String,
        type: String,
        content: String,
        latitude: Double,
        longitude: Double,
        question: String? = null,
        solution: String? = null,
        fakeLatitude: Double? = null,
        fakeLongitude: Double? = null
    ) {
        val geocache = Geocache(
            id = 0,
            type = type,
            title = title,
            content = content,
            latitude = latitude,
            longitude = longitude,
            fakeLatitude = fakeLatitude,
            fakeLongitude = fakeLongitude,
            question = question,
            solution = solution,
            locked = (solution != null),
            found = false,
        )

        withContext(Dispatchers.IO) { repository.save(geocache) }
    }

}