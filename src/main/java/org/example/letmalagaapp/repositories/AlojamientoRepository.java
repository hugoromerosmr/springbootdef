package org.example.letmalagaapp.repositories;

import org.example.letmalagaapp.models.Alojamiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Repositorio para manejar las operaciones de la base de datos relacionadas con los alojamientos.
 */
@Repository
public interface AlojamientoRepository extends MongoRepository<Alojamiento, String> {

    /**
     * Encuentra alojamientos cuyo nombre contiene la cadena dada, ignorando mayúsculas y minúsculas.
     *
     * @param nombre la cadena a buscar en los nombres de los alojamientos
     * @return una lista de alojamientos que coinciden con el criterio de búsqueda
     */
    List<Alojamiento> findByNombreContainingIgnoreCase(String nombre);

}