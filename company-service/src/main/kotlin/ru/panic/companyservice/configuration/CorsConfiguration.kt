package ru.panic.companyservice.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class CorsConfiguration : WebMvcConfigurer {
    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/api/company/**")
                .allowedHeaders("*")
                .allowedMethods("POST")
                .allowedOrigins("http://localhost:3000")
    }
}