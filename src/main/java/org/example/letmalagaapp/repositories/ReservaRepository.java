package org.example.letmalagaapp.repositories;

import org.example.letmalagaapp.models.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    List<Reserva> findByAlojamientoId(String alojamientoId);
}
