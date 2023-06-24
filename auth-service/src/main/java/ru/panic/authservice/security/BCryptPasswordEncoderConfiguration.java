package ru.panic.authservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BCryptPasswordEncoderConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
}
