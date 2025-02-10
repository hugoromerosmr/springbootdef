package org.example.letmalagaapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Configuración de CORS para la aplicación.
 */
@Configuration
public class WebConfig {

    /**
     * Configura la fuente de configuración de CORS.
     *
     * @return la fuente de configuración de CORS
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");  // Permite todas las fuentes de origen
        configuration.addAllowedMethod("*");  // Permite todos los métodos HTTP
        configuration.addAllowedHeader("*");  // Permite todas las cabeceras

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}