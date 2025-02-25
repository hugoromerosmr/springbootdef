package org.example.letmalagaapp.repositories;

import org.example.letmalagaapp.models.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio para manejar las operaciones de la base de datos relacionadas con las reservas.
 */
public interface ReservaRepository extends MongoRepository<Reserva, String> {

    /**
     * Encuentra reservas por el ID del alojamiento.
     *
     * @param alojamientoId el ID del alojamiento
     * @return una lista de reservas que coinciden con el ID del alojamiento
     */
    List<Reserva> findByAlojamientoId(String alojamientoId);

    /**
     * Encuentra reservas por el ID del usuario.
     *
     * @param usuarioId el ID del usuario
     * @return una lista de reservas asociadas al usuario
     */
    List<Reserva> findByUsuarioId(String usuarioId);

    /**
     * Encuentra reservas de un usuario ordenadas por fecha de inicio.
     */
    List<Reserva> findByUsuarioIdOrderByFechaInicioAsc(String usuarioId);

    /**
     * Verifica si ya existe una reserva en las fechas seleccionadas.
     * Se usa para evitar superposición de reservas.
     */
    boolean existsByAlojamientoIdAndFechaInicioBeforeAndFechaFinAfter(
            String alojamientoId, LocalDate fechaFin, LocalDate fechaInicio);

    /**
     * Encuentra reservas activas (donde la fecha de fin aún no ha pasado).
     */
    List<Reserva> findByUsuarioIdAndFechaFinAfterOrderByFechaInicioAsc(String usuarioId, LocalDate hoy);

    /**
     * Encuentra reservas de un usuario por estado (ejemplo: "pagada", "cancelada").
     */
    List<Reserva> findByUsuarioIdAndEstado(String usuarioId, String estado);

    boolean existsByAlojamientoIdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(String alojamientoId, LocalDate fechaFin, LocalDate fechaInicio);
}
