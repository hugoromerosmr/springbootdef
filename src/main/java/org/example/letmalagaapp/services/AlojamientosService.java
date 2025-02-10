package org.example.letmalagaapp.services;

import org.example.letmalagaapp.models.Alojamiento;
import org.example.letmalagaapp.models.Reserva;
import org.example.letmalagaapp.repositories.AlojamientoRepository;
import org.example.letmalagaapp.repositories.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para manejar las operaciones relacionadas con los alojamientos.
 */
@Service
public class AlojamientosService {

    private final AlojamientoRepository alojamientoRepository;
    private final ReservaRepository reservaRepository;

    public AlojamientosService(AlojamientoRepository alojamientoRepository, ReservaRepository reservaRepository) {
        this.alojamientoRepository = alojamientoRepository;
        this.reservaRepository = reservaRepository;
    }

    /**
     * Obtiene todos los alojamientos disponibles en la base de datos.
     *
     * @return una lista de todos los alojamientos
     */
    public List<Alojamiento> obtenerTodosLosAlojamientos() {
        return alojamientoRepository.findAll();
    }

    /**
     * Obtiene los alojamientos disponibles en un rango de fechas.
     *
     * @param fechaInicioStr la fecha de inicio en formato de cadena
     * @param fechaFinStr la fecha de fin en formato de cadena
     * @return una lista de alojamientos disponibles en el rango de fechas dado
     */
    public List<Alojamiento> obtenerAlojamientosDisponibles(String fechaInicioStr, String fechaFinStr) {
        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
        LocalDate fechaFin = LocalDate.parse(fechaFinStr);

        List<Reserva> reservasEnFechas = reservaRepository.findAll().stream()
                .filter(reserva -> {
                    LocalDate inicioReserva = reserva.getFechaInicio();
                    LocalDate finReserva = reserva.getFechaFin();

                    if (inicioReserva == null || finReserva == null) {
                        return false;
                    }

                    // Verifica si hay superposición de fechas
                    return (inicioReserva.isBefore(fechaFin) && finReserva.isAfter(fechaInicio));
                })
                .collect(Collectors.toList());

        List<String> alojamientosOcupados = reservasEnFechas.stream()
                .map(Reserva::getAlojamientoId)
                .toList();

        return alojamientoRepository.findAll().stream()
                .filter(alojamiento -> !alojamientosOcupados.contains(alojamiento.getId()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un alojamiento por su ID.
     *
     * @param id el ID del alojamiento
     * @return el alojamiento correspondiente al ID dado, o null si no se encuentra
     */
    public Alojamiento obtenerAlojamientoPorId(String id) {
        return alojamientoRepository.findById(id).orElse(null);
    }

    /**
     * Busca alojamientos por nombre.
     *
     * @param nombre el nombre del alojamiento a buscar
     * @return una lista de alojamientos que coinciden con el nombre dado
     */
    public List<Alojamiento> buscarAlojamientosPorNombre(String nombre) {
        return alojamientoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}