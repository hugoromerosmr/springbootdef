package org.example.letmalagaapp.repositories;

import org.example.letmalagaapp.models.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    List<Reserva> findByAlojamientoId(String alojamientoId);

    @Query("{ 'fechaInicio': { $lte: ?1 }, 'fechaFin': { $gte: ?0 } }")
    List<Reserva> findOverlappingReservations(LocalDate fechaInicioBuscada, LocalDate fechaFinBuscada);
}
