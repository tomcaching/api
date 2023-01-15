package dev.vrba.caches.api.service

import dev.vrba.caches.api.domain.Geocache
import dev.vrba.caches.api.exception.GeocacheNotFoundException
import dev.vrba.caches.api.exception.InvalidGeocacheParametersException
import dev.vrba.caches.api.exception.InvalidGeocacheSolutionException
import dev.vrba.caches.api.repository.GeocacheRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
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
        validateGeocacheParameters(type, question, solution, fakeLatitude, fakeLongitude)

        val locked = type === "mystery"
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
            locked = locked,
            found = false,
        )

        withContext(Dispatchers.IO) { repository.save(geocache) }
    }

    suspend fun updateGeocache(id: Int, title: String, type: String, content: String, latitude: Double, longitude: Double, question: String?, solution: String?, fakeLatitude: Double?, fakeLongitude: Double?) {
        validateGeocacheParameters(type, question, solution, fakeLatitude, fakeLongitude)

        withContext(Dispatchers.IO) {
            val cache = repository.findByIdOrNull(id) ?: throw GeocacheNotFoundException
            val locked = type === "mystery"
            val updated = cache.copy(
                type = type,
                title = title,
                content = content,
                latitude = latitude,
                longitude = longitude,
                fakeLatitude = fakeLatitude,
                fakeLongitude = fakeLongitude,
                question = question,
                solution = solution,
                locked = locked
            )

            repository.save(updated)
        }
    }

    suspend fun deleteGeocache(id: Int) {
        withContext(Dispatchers.IO) {
            repository.deleteById(id)
        }
    }

    suspend fun unlockGeocache(id: Int, solution: String) {
        withContext(Dispatchers.IO) {
            val cache = repository.findByIdOrNull(id)

            if (cache == null || cache.type != "mystery" || cache.solution != solution) {
                throw InvalidGeocacheSolutionException
            }

            repository.save(cache.copy(locked = false))
        }
    }

    private fun validateGeocacheParameters(type: String, question: String?, solution: String?, fakeLatitude: Double?, fakeLongitude: Double?) {
        if (type == "traditional") {
            return
        }

        // Mystery caches must have all the following set
        if (question == null || solution == null || fakeLatitude == null || fakeLongitude == null) {
            throw InvalidGeocacheParametersException
        }
    }

}