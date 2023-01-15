package dev.vrba.caches.api.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
object GeocacheNotFoundException : RuntimeException("Geocache was not found")