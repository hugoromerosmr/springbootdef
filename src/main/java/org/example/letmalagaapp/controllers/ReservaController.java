package org.example.letmalagaapp.controllers;

import org.example.letmalagaapp.models.Reserva;
import org.example.letmalagaapp.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    /**
     * Muestra el formulario de reserva para un alojamiento dado.
     * Se asume que se recibe el ID del alojamiento en la URL.
     */
    @GetMapping("/reservar/{alojamientoId}")
    public String mostrarFormularioReserva(@PathVariable String alojamientoId, Model model) {
        // Creamos una nueva reserva y asignamos el ID del alojamiento
        Reserva reserva = new Reserva();
        reserva.setAlojamientoId(alojamientoId);
        model.addAttribute("reserva", reserva);
        return "reservaForm";  // Vista Thymeleaf: src/main/resources/templates/reservaForm.html
    }

    /**
     * Procesa el formulario de reserva y guarda la reserva en la base de datos.
     */
    @PostMapping("/reservar/{alojamientoId}")
    public String procesarReserva(@PathVariable String alojamientoId,
                                  @ModelAttribute("reserva") Reserva reserva,
                                  Model model) {
        // Aseguramos que la reserva tenga asignado el ID del alojamiento
        reserva.setAlojamientoId(alojamientoId);
        // Aquí podrías agregar lógica adicional, como calcular el total o definir el estado inicial.
        reservaRepository.save(reserva);
        model.addAttribute("mensaje", "¡Reserva realizada exitosamente!");
        return "reservaConfirmacion"; // Vista Thymeleaf: src/main/resources/templates/reservaConfirmacion.html
    }
}
