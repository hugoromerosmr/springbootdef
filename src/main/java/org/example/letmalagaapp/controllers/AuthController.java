package org.example.letmalagaapp.controllers;

import org.example.letmalagaapp.security.AuthService;
import org.example.letmalagaapp.models.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador para manejar las solicitudes relacionadas con la autenticación.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Constructor para AuthController.
     *
     * @param authService el servicio para manejar las operaciones de autenticación
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Muestra la página de inicio de sesión.
     *
     * @return el nombre de la vista para la página de inicio de sesión
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Muestra la página de registro.
     *
     * @return el nombre de la vista para la página de registro
     */
    @GetMapping("/register")
    public String registerPage() {
        return "indexregister";
    }

    /**
     * Maneja la solicitud de inicio de sesión.
     *
     * @param username el nombre de usuario
     * @param password la contraseña
     * @param model el modelo para agregar atributos
     * @return redirige a la galería si el inicio de sesión es exitoso, de lo contrario, muestra la página de inicio de sesión con un mensaje de error
     */
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

    /**
     * Maneja la solicitud de registro.
     *
     * @param usuario el objeto Usuario con los datos del nuevo usuario
     * @return una respuesta con un mensaje de éxito si el registro es exitoso
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        usuario.setPassword(usuario.getPassword());
        Usuario newUser = authService.registrar(usuario);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado con éxito"));
    }
}