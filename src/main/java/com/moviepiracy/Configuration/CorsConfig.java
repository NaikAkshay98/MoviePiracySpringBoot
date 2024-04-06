package com.moviepiracy.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    // The CorsFilter bean might still be useful if you need more customized CORS configurations 
    // that cannot be achieved with WebMvcConfigurer
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // This is very open, consider locking it down in production
        config.addAllowedMethod("*"); // Allow all HTTP methods
        config.addAllowedHeader("*"); // Allow all headers
        source.registerCorsConfiguration("/**", config); // Applies CORS configuration to all endpoints
        return new CorsFilter(source);
    }
    
    @Bean // Ensure this method is a Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Now specifically targeting your API endpoints
                        .allowedOrigins("http://localhost:3000") // Specifically allowing your React app origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specifying allowed methods
                        .allowedHeaders("*") // Allowing all headers
                        .allowCredentials(true); // Allows credentials, such as cookies, to be sent
            }
        };
    }
}
