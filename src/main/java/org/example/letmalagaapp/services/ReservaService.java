package org.example.letmalagaapp.services;

import org.example.letmalagaapp.models.Reserva;
import org.example.letmalagaapp.repositories.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /**
     * Obtiene todas las reservas en la base de datos
     */
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    /**
     * Obtiene las reservas que coinciden con el rango de fechas proporcionado
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
     * Obtiene una reserva por su ID
     */
    public Reserva obtenerReservaPorId(String id) {
        return reservaRepository.findById(id).orElse(null);
    }
}
