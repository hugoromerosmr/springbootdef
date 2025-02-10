package org.example.letmalagaapp.services;

import org.example.letmalagaapp.models.Reserva;
import org.example.letmalagaapp.repositories.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para manejar las operaciones relacionadas con las reservas.
 */
@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /**
     * Obtiene todas las reservas en la base de datos.
     *
     * @return una lista de todas las reservas
     */
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    /**
     * Obtiene las reservas que coinciden con el rango de fechas proporcionado.
     *
     * @param fechaInicio la fecha de inicio del rango
     * @param fechaFin la fecha de fin del rango
     * @return una lista de reservas que coinciden con el rango de fechas
     */
    public List<Reserva> obtenerReservasEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return reservaRepository.findAll().stream()
                .filter(reserva -> {
                    LocalDate inicioReserva = reserva.getFechaInicio();
                    LocalDate finReserva = reserva.getFechaFin();

                    // Evita null pointer exception
                    if (inicioReserva == null || finReserva == null) {
                        return false;
                    }

                    // Verifica si hay superposición de fechas
                    return (inicioReserva.isBefore(fechaFin) && finReserva.isAfter(fechaInicio));
                })
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una reserva por su ID.
     *
     * @param id el ID de la reserva
     * @return la reserva correspondiente al ID dado, o null si no se encuentra
     */
    public Reserva obtenerReservaPorId(String id) {
        return reservaRepository.findById(id).orElse(null);
    }
}