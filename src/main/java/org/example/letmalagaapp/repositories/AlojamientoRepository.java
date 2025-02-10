package org.example.letmalagaapp.repositories;

import org.example.letmalagaapp.models.Alojamiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AlojamientoRepository extends MongoRepository<Alojamiento, String> {

    List<Alojamiento> findByNombreContainingIgnoreCase(String nombre);

    List<Alojamiento> findByIdNotIn(Set<String> ids);


}
