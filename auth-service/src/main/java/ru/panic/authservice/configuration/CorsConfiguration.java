package ru.panic.authservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/api/auth/signIn")
                .allowedHeaders("*")
                .allowedMethods("POST")
                .allowedOrigins("http://localhost:3000");
        corsRegistry.addMapping("/api/auth/signUp")
                .allowedHeaders("*")
                .allowedMethods("POST")
                .allowedOrigins("http://localhost:3000");
        corsRegistry.addMapping("/api/auth/getInfoByJwt")
                .allowedHeaders("*")
                .allowedMethods("POST")
                .allowedOrigins("*");
    }
}
