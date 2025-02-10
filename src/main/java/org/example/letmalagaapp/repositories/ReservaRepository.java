package org.example.letmalagaapp.repositories;

import org.example.letmalagaapp.models.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

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
}