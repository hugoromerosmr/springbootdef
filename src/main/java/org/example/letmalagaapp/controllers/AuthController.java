package org.example.letmalagaapp.controllers;

import org.example.letmalagaapp.security.AuthService;
import org.example.letmalagaapp.models.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller // ✅ Cambiado de @RestController a @Controller para devolver HTML
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ✅ Página de Login (Renderiza login.html)
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // 📌 Esto debe coincidir con login.html en /resources/templates/
    }

    // ✅ Página de Registro (Renderiza indexregister.html)
    @GetMapping("/register")
    public String registerPage() {
        return "indexregister";  // 📌 Esto debe coincidir con indexregister.html en /resources/templates/
    }

    // ✅ Endpoint para el login - Responde con JSON
    @PostMapping("/login")
    @ResponseBody // 📌 Esto es necesario para que devuelva JSON en lugar de una vista
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());

        if (token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login exitoso");
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
        }
    }

    // ✅ Endpoint para el registro - Responde con JSON
    @PostMapping("/register")
    @ResponseBody // 📌 Esto es necesario para que devuelva JSON en lugar de una vista
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        usuario.setPassword(usuario.getPassword());
        Usuario newUser = authService.registrar(usuario);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado con éxito"));
    }

    // DTO para el login
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
