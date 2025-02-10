package org.example.letmalagaapp.repositories;

import org.example.letmalagaapp.models.Alojamiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlojamientoRepository extends MongoRepository<Alojamiento, String> {
    // Puedes agregar consultas personalizadas aquí si lo necesitas
    // Por ejemplo, buscar alojamientos por nombre:
    List<Alojamiento> findByNombreContaining(String nombre);


}
