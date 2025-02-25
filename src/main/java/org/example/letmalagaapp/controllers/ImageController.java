package org.example.letmalagaapp.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Controlador para manejar las solicitudes relacionadas con las imágenes.
 */
@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "https://successful-alignment.up.railway.app")
public class ImageController {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    /**
     * Obtiene una imagen por su nombre de archivo.
     *
     * @param filename el nombre del archivo de la imagen
     * @return la imagen en formato de bytes si se encuentra, de lo contrario, un estado 404
     */
    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
        try {
            Path imagePath = Paths.get(UPLOAD_DIR, filename);
            byte[] imageBytes = Files.readAllBytes(imagePath);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(imagePath));

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}