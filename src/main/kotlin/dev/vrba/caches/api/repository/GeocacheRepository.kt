package dev.vrba.caches.api.repository

import dev.vrba.caches.api.domain.Geocache
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GeocacheRepository : CrudRepository<Geocache, Int>