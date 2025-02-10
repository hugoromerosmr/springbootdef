package org.example.letmalagaapp.controllers;

import org.example.letmalagaapp.models.Alojamiento;
import org.example.letmalagaapp.services.AlojamientosService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador para manejar las solicitudes relacionadas con "Alojamiento".
 */
@Controller
public class AlojamientoController {

    private final AlojamientosService alojamientosService;

    /**
     * Constructor para AlojamientoController.
     *
     * @param alojamientosService el servicio para manejar las operaciones de alojamiento
     */
    public AlojamientoController(AlojamientosService alojamientosService) {
        this.alojamientosService = alojamientosService;
    }

    /**
     * Muestra la galería completa de alojamientos.
     *
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista para la galería
     */
    @GetMapping("/galeria")
    public String mostrarGaleria(Model model) {
        List<Alojamiento> alojamientos = alojamientosService.obtenerTodosLosAlojamientos();
        model.addAttribute("alojamientos", alojamientos);
        return "galeria"; // Vista en templates/galeria.html
    }

    /**
     * Busca alojamientos disponibles entre dos fechas.
     * Las fechas se reciben en formato ISO (yyyy-MM-dd) y se convierten a LocalDate.
     *
     * @param fechaInicio la fecha de inicio del período de búsqueda
     * @param fechaFin la fecha de fin del período de búsqueda
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista para la galería
     */
    @GetMapping("/alojamientos/disponibles")
    public String buscarAlojamientosDisponibles(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            Model model) {

        List<Alojamiento> alojamientosDisponibles = alojamientosService.obtenerAlojamientosDisponibles(fechaInicio.toString(), fechaFin.toString());
        model.addAttribute("alojamientos", alojamientosDisponibles);

        // Agrega las fechas al modelo para que se mantengan en el formulario
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);

        return "galeria"; // La misma vista de la galería
    }

    /**
     * Muestra la vista de detalle de un alojamiento específico.
     *
     * @param id el ID del alojamiento
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista para la página de detalle
     */
    @GetMapping("/alojamiento")
    public String mostrarDetalle(@RequestParam("id") String id, Model model) {
        Alojamiento alojamiento = alojamientosService.obtenerAlojamientoPorId(id);
        if (alojamiento == null) {
            return "redirect:/galeria"; // Redirige a la galería si no existe el alojamiento
        }
        model.addAttribute("alojamiento", alojamiento);
        return "detalle"; // Vista en templates/detalle.html
    }

    /**
     * Busca alojamientos por nombre.
     *
     * @param nombre el nombre del alojamiento a buscar
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista para la galería con los alojamientos filtrados
     */
    @GetMapping("/alojamientos/buscar")
    public String buscarAlojamientosPorNombre(@RequestParam("nombre") String nombre, Model model) {
        List<Alojamiento> alojamientos = alojamientosService.buscarAlojamientosPorNombre(nombre);
        model.addAttribute("alojamientos", alojamientos);
        // Deja en el modelo el valor del nombre para que se mantenga en el formulario
        model.addAttribute("nombre", nombre);
        return "galeria"; // Recarga la misma vista de la galería con los alojamientos filtrados por nombre
    }
}