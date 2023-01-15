package dev.vrba.caches.api.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

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

}