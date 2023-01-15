package dev.vrba.caches.api.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URI
import java.security.Principal

@Controller
@RequestMapping("/")
class HomepageController {

    @GetMapping
    suspend fun redirect(): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.TEMPORARY_REDIRECT)
            .location(URI.create("https://tomcaching.fun"))
            .build()
    }

    @GetMapping("/api/check-password")
    suspend fun check(@AuthenticationPrincipal principal: Principal): ResponseEntity<Map<String, String>> {
        return ResponseEntity.ok(mapOf(
            "message" to "Password is valid.",
            "user" to principal.name
        ))
    }

}