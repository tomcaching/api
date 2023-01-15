package dev.vrba.caches.api.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http {
            authorizeExchange {
                authorize("/", permitAll)
                authorize("/api/caches", permitAll)
                authorize("/api/caches/**", authenticated)
            }

            csrf { disable() }
            formLogin { disable() }

            cors {}
            httpBasic {}
        }
    }

    @Bean
    fun corsFilter(): CorsWebFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration().apply {
            allowCredentials = false
            allowedHeaders = listOf("*")
            allowedMethods = listOf("*")
            allowedOrigins = listOf("*")
        }

        source.registerCorsConfiguration("/api/**", config)

        return CorsWebFilter(source)
    }
}