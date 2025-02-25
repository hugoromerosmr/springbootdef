package org.example.letmalagaapp.controllers;

import org.example.letmalagaapp.models.Alojamiento;
import org.example.letmalagaapp.models.Reserva;
import org.example.letmalagaapp.models.ReservaDTO;
import org.example.letmalagaapp.repositories.AlojamientoRepository;
import org.example.letmalagaapp.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
     * Muestra el formulario de reserva para un alojamiento específico.
     *
     * @param alojamientoId el ID del alojamiento
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista para el formulario de reserva
     */
    @GetMapping("/reservar/{alojamientoId}")
    public String mostrarFormularioReserva(@PathVariable String alojamientoId, Model model) {
        Optional<Alojamiento> alojamientoOpt = alojamientoRepository.findById(alojamientoId);
        if (alojamientoOpt.isPresent()) {
            model.addAttribute("alojamiento", alojamientoOpt.get());

            Reserva reserva = new Reserva();
            reserva.setAlojamientoId(alojamientoId);
            model.addAttribute("reserva", reserva);
            return "reservaForm";
        } else {
            model.addAttribute("mensaje", "Alojamiento no encontrado");
            return "error";
        }
    }

    /**
     * Procesa la reserva de un alojamiento.
     *
     * @param alojamientoId el ID del alojamiento
     * @param reserva la reserva a procesar
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista de confirmación de reserva
     */
    @PostMapping("/reservar/{alojamientoId}")
    public String procesarReserva(@PathVariable String alojamientoId,
                                  @ModelAttribute("reserva") Reserva reserva,
                                  Model model) {
        Optional<Alojamiento> alojamientoOpt = alojamientoRepository.findById(alojamientoId);
        if (alojamientoOpt.isPresent()) {
            Alojamiento alojamiento = alojamientoOpt.get();
            double precioPorNoche = alojamiento.getPrecio_minimo();

            if (reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
                model.addAttribute("mensaje", "La fecha de inicio no puede ser posterior a la fecha de fin.");
                return "reservaForm";
            }

            String usuarioEmail = getLoggedInUserId(); // Ahora guardamos el email del usuario autenticado
            if (usuarioEmail == null) {
                model.addAttribute("mensaje", "Debes iniciar sesión para hacer una reserva.");
                return "login";
            }

            reserva.setUsuarioId(usuarioEmail); // ✅ Guardamos el email en lugar de "usuario123"

            long dias = ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());
            reserva.setTotal(precioPorNoche * Math.max(dias, 1));
            reserva.setEstado("pagada");

            reservaRepository.save(reserva);

            model.addAttribute("mensaje", "¡Reserva realizada exitosamente! Total: " + reserva.getTotal());
            return "reservaConfirmacion";
        } else {
            model.addAttribute("mensaje", "Alojamiento no encontrado");
            return "error";
        }
    }

    /**
     * Muestra las reservas del usuario autenticado.
     *
     * @param model el modelo para agregar atributos
     * @return el nombre de la vista para las reservas del usuario
     */
    @GetMapping("/mis-reservas")
    public String verMisReservas(Model model) {
        String usuarioId = getLoggedInUserId();
        System.out.println("Usuario autenticado: " + usuarioId); // 🛠 Debug

        List<Reserva> reservas = reservaRepository.findByUsuarioId(usuarioId);
        System.out.println("Reservas encontradas: " + reservas.size()); // 🛠 Debug

        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva reserva : reservas) {
            ReservaDTO reservaDTO = new ReservaDTO();
            reservaDTO.setAlojamientoId(reserva.getAlojamientoId());
            reservaDTO.setFechaInicio(String.valueOf(reserva.getFechaInicio()));
            reservaDTO.setFechaFin(String.valueOf(reserva.getFechaFin()));
            reservaDTO.setTotal(reserva.getTotal());
            reservaDTO.setEstado(reserva.getEstado());

            Optional<Alojamiento> alojamientoOpt = alojamientoRepository.findById(reserva.getAlojamientoId());
            if (alojamientoOpt.isPresent()) {
                reservaDTO.setNombreAlojamiento(alojamientoOpt.get().getNombre());
                reservaDTO.setImagenAlojamiento(alojamientoOpt.get().getImagenes().get(0));
            } else {
                reservaDTO.setNombreAlojamiento("Alojamiento no encontrado");
            }

            reservasDTO.add(reservaDTO);
        }

        if (reservasDTO.isEmpty()) {
            model.addAttribute("mensaje", "No tienes reservas activas.");
        }

        model.addAttribute("reservas", reservasDTO);
        return "reservas";
    }

    /**
     * Obtiene el ID del usuario autenticado.
     *
     * @return el ID del usuario autenticado, o null si no hay usuario autenticado
     */
    private String getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername(); // Devuelve el email del usuario autenticado
        }

        return null;
    }
}