package org.example.letmalagaapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad para la aplicación.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http el objeto HttpSecurity para configurar la seguridad web
     * @return la cadena de filtros de seguridad configurada
     * @throws Exception si ocurre un error durante la configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors() // Permite solicitudes CORS
                .and()
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite todas las solicitudes sin autenticación
                )
                .csrf().disable(); // Desactiva CSRF (solo si es necesario)

        return http.build();
    }
}