package org.example.letmalagaapp.controllers;

import org.example.letmalagaapp.security.AuthService;
import org.example.letmalagaapp.models.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "indexregister";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        String token = authService.login(username, password);

        if (token != null) {
            return "redirect:/galeria";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        usuario.setPassword(usuario.getPassword());
        Usuario newUser = authService.registrar(usuario);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado con éxito"));
    }
}