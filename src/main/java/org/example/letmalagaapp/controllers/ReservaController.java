package org.example.letmalagaapp.controllers;

import org.example.letmalagaapp.models.Alojamiento;
import org.example.letmalagaapp.models.Reserva;
import org.example.letmalagaapp.repositories.AlojamientoRepository;
import org.example.letmalagaapp.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * Controlador para manejar las solicitudes relacionadas con las reservas.
 */
@Controller
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private AlojamientoRepository alojamientoRepository;

    /**
     * Muestra el formulario de reserva para el alojamiento dado.
     * El usuario solo debe seleccionar: fechaInicio, fechaFin, personas y moneda.
     *
     * @param alojamientoId el ID del alojamiento
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista para el formulario de reserva
     */
    @GetMapping("/reservar/{alojamientoId}")
    public String mostrarFormularioReserva(@PathVariable String alojamientoId, Model model) {
        Optional<Alojamiento> alojamientoOpt = alojamientoRepository.findById(alojamientoId);
        if (alojamientoOpt.isPresent()) {
            Alojamiento alojamiento = alojamientoOpt.get();
            // Agrega el alojamiento al modelo para usarlo en la vista
            model.addAttribute("alojamiento", alojamiento);

            // Crea la reserva y asigna el alojamientoId
            Reserva reserva = new Reserva();
            reserva.setAlojamientoId(alojamientoId);
            model.addAttribute("reserva", reserva);
            return "reservaForm";  // Vista en src/main/resources/templates/reservaForm.html
        } else {
            model.addAttribute("mensaje", "Alojamiento no encontrado");
            return "error";  // O una página de error personalizada
        }
    }

    /**
     * Procesa la reserva para el alojamiento dado.
     *
     * @param alojamientoId el ID del alojamiento
     * @param reserva el objeto Reserva con los datos de la reserva
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista para la confirmación de la reserva
     */
    @PostMapping("/reservar/{alojamientoId}")
    public String procesarReserva(@PathVariable String alojamientoId,
                                  @ModelAttribute("reserva") Reserva reserva,
                                  Model model) {
        Optional<Alojamiento> alojamientoOpt = alojamientoRepository.findById(alojamientoId);
        if (alojamientoOpt.isPresent()) {
            Alojamiento alojamiento = alojamientoOpt.get();
            double precioPorNoche = alojamiento.getPrecio_minimo();

            // Calcular número de días (al menos 1)
            long dias = ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());
            if (dias < 1) {
                dias = 1;
            }
            reserva.setTotal(precioPorNoche * dias);

            // Asigna el usuario logeado (simulado)
            reserva.setUsuarioId(getLoggedInUserId());

            // Estado por defecto: "pagada"
            reserva.setEstado("pagada");

            reserva.setAlojamientoId(alojamientoId);
            reservaRepository.save(reserva);
            model.addAttribute("mensaje", "¡Reserva realizada exitosamente! Total: " + reserva.getTotal());
            return "reservaConfirmacion";
        } else {
            model.addAttribute("mensaje", "Alojamiento no encontrado");
            return "error";
        }
    }

    /**
     * Simulación de obtención del usuario logeado.
     *
     * @return el ID del usuario logeado
     */
    private String getLoggedInUserId() {
        return "usuario123";  // Valor de ejemplo
    }
}