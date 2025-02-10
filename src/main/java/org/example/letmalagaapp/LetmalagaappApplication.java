package org.example.letmalagaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Clase principal para la aplicación Letmalagaapp.
 */
@EnableWebMvc
@SpringBootApplication
public class LetmalagaappApplication {

    /**
     * Método principal para ejecutar la aplicación.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(LetmalagaappApplication.class, args);
    }

    /**
     * Configura los mapeos CORS para la aplicación.
     *
     * @param registry el registro de mapeos CORS
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:3000"); // Asegúrate de poner el origen correcto
    }
}