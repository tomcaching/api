package dev.vrba.caches.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CachesApiApplication

fun main(args: Array<String>) {
    runApplication<CachesApiApplication>(*args)
}
