package dev.vrba.caches.api

import dev.vrba.caches.api.configuration.AdminConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AdminConfiguration::class)
class CachesApiApplication

fun main(args: Array<String>) {
    runApplication<CachesApiApplication>(*args)
}
