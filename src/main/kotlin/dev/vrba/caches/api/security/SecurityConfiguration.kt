package dev.vrba.caches.api.security

import dev.vrba.caches.api.configuration.AdminConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
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
                authorize("/api/caches/unlock/*", permitAll)
                authorize("/api/caches/mark-found/*", permitAll)
                authorize("/api/**", hasRole("ADMIN"))
            }

            csrf { disable() }
            formLogin { disable() }

            cors {}
            httpBasic {}
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService(configuration: AdminConfiguration, encoder: PasswordEncoder): ReactiveUserDetailsService {
        val user = User.builder()
            .username("admin")
            .password(encoder.encode(configuration.password))
            .roles("ADMIN")
            .build()

        return MapReactiveUserDetailsService(user)
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