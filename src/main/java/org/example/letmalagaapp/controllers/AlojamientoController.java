package org.example.letmalagaapp.controllers;

import org.example.letmalagaapp.models.Alojamiento;
import org.example.letmalagaapp.services.AlojamientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AlojamientoController {

    @Autowired
    private AlojamientosService alojamientoService;

    // Mostrar todos los alojamientos en la galería
    @GetMapping("/galeria")
    public String mostrarGaleria(Model model) {
        List<Alojamiento> alojamientos = alojamientoService.getAllAlojamientos();  // Obtener todos los alojamientos
        model.addAttribute("alojamientos", alojamientos);  // Agregar la lista al modelo
        return "galeria";  // Esta es la vista que se renderizará (galeria.html)
    }
    @GetMapping("/upload")
    public String upload(Model model) {
        return "upload";  // Esta es la vista que se renderizará (upload.html)
    }
}
