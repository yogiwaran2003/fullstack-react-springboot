package com.example.stickers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            var context = SecurityContextHolder.getContext();
            var auth = context != null ? context.getAuthentication() : null;

            if (auth != null && auth.isAuthenticated()) {
                return Optional.ofNullable(auth.getName());
            }
            // Fallback for non-auth flows (e.g., registration)
            return Optional.of("system");
        };
    }
}
