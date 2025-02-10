package org.example.letmalagaapp.controllers;

import org.example.letmalagaapp.models.Alojamiento;
import org.example.letmalagaapp.services.AlojamientosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AlojamientoController {

    private final AlojamientosService alojamientosService;

    public AlojamientoController(AlojamientosService alojamientosService) {
        this.alojamientosService = alojamientosService;
    }

    /**
     * Muestra la galería de alojamientos.
     */
    @GetMapping("/galeria")
    public String mostrarGaleria(Model model) {
        List<Alojamiento> alojamientos = alojamientosService.obtenerTodosLosAlojamientos();
        model.addAttribute("alojamientos", alojamientos);
        return "galeria"; // Apunta a templates/galeria.html
    }

    /**
     * Busca alojamientos disponibles entre dos fechas.
     */
    @GetMapping("/alojamientos/disponibles")
    public String buscarAlojamientosDisponibles(@RequestParam String fechaInicio,
                                                @RequestParam String fechaFin,
                                                Model model) {
        List<Alojamiento> alojamientosDisponibles = alojamientosService.obtenerAlojamientosDisponibles(fechaInicio, fechaFin);
        model.addAttribute("alojamientos", alojamientosDisponibles);
        return "galeria"; // Recarga la misma galería pero con alojamientos filtrados
    }

    /**
     * Muestra la vista de detalle de un alojamiento específico.
     */
    @GetMapping("/alojamiento")
    public String mostrarDetalle(@RequestParam("id") String id, Model model) {
        Alojamiento alojamiento = alojamientosService.obtenerAlojamientoPorId(id);
        if (alojamiento == null) {
            return "redirect:/galeria"; // Si el ID no existe, redirige a la galería
        }
        model.addAttribute("alojamiento", alojamiento);
        return "detalle"; // Apunta a templates/detalle.html
    }
}
