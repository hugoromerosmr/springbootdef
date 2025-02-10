package org.example.letmalagaapp.security;

import org.example.letmalagaapp.models.Usuario;
import org.example.letmalagaapp.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

/**
 * Servicio de autenticación para manejar el registro y login de usuarios.
 */
@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Key SECRET_KEY = Keys.hmacShaKeyFor("supersecretkey12345678901234567890123456789012".getBytes());

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param usuario el usuario a registrar
     * @return el usuario registrado
     */
    public Usuario registrar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    /**
     * Inicia sesión verificando las credenciales y generando un token.
     *
     * @param email el correo electrónico del usuario
     * @param password la contraseña del usuario
     * @return el token JWT si las credenciales son correctas, null en caso contrario
     */
    public String login(String email, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent() && passwordEncoder.matches(password, usuario.get().getPassword())) {
            return generateToken(usuario.get().getEmail());
        }
        return null;
    }

    /**
     * Genera un token JWT para el usuario.
     *
     * @param email el correo electrónico del usuario
     * @return el token JWT
     */
    private String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Expira en 1 día
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}