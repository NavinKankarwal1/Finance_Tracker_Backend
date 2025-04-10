package com.example.expensetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults()) // ✅ Enables CORS
                .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()); // ✅ Allow all endpoints for testing

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // allow cookies/tokens in requests
        config.addAllowedOriginPattern("*"); // ✅ allow all origins (even with credentials)
        config.addAllowedHeader("*"); // ✅ allow all headers (Authorization, Content-Type, etc.)
        config.addAllowedMethod("*"); // ✅ allow all HTTP methods (GET, POST, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // ✅ apply to all paths
        return new CorsFilter(source);
    }
}