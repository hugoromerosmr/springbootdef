package org.example.letmalagaapp.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.letmalagaapp.models.Usuario;
import org.example.letmalagaapp.security.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import org.springframework.http.ResponseEntity;

/**
 * Controlador para manejar las solicitudes relacionadas con la autenticación.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Muestra la página de inicio de sesión.
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Muestra la página de registro.
     */
    @GetMapping("/register")
    public String registerPage() {
        return "indexregister";
    }

    /**
     * Maneja la solicitud de inicio de sesión con autenticación en Spring Security.
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            // Realiza la autenticación en Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Establece la sesión en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/mis-reservas"; // Redirige a la página de reservas tras el login
        } catch (Exception e) {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

    /**
     * Maneja la solicitud de registro.
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        usuario.setPassword(usuario.getPassword());
        Usuario newUser = authService.registrar(usuario);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado con éxito"));
    }

    /**
     * Maneja la solicitud de cierre de sesión.
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();

        request.getSession().invalidate();

        Cookie logoutCookie = new Cookie("JSESSIONID", null);
        logoutCookie.setPath("/");
        logoutCookie.setMaxAge(0);  // Invalida la cookie
        response.addCookie(logoutCookie);

        return "redirect:/auth/login?logout";
    }
}