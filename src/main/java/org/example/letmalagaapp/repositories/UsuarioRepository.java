package org.example.letmalagaapp.repositories;

import org.example.letmalagaapp.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repositorio para manejar las operaciones de la base de datos relacionadas con los usuarios.
 */
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    /**
     * Encuentra un usuario por su correo electrónico.
     *
     * @param email el correo electrónico del usuario
     * @return un Optional que contiene el usuario si se encuentra, o vacío si no
     */
    Optional<Usuario> findByEmail(String email);
}