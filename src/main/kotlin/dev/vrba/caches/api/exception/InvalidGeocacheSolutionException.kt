package dev.vrba.caches.api.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
object InvalidGeocacheSolutionException : RuntimeException("The provided solution")