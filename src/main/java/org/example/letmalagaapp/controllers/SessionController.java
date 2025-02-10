package org.example.letmalagaapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para manejar las solicitudes relacionadas con la sesión.
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    /**
     * Obtiene la información de la sesión actual.
     *
     * @param request la solicitud HTTP
     * @return una cadena con el ID de la sesión si hay una sesión activa, de lo contrario, un mensaje indicando que no hay sesión activa
     */
    @GetMapping("/info")
    public String getSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "No active session";
        }
        return "Session ID: " + session.getId();
    }

    /**
     * Invalida la sesión actual.
     *
     * @param request la solicitud HTTP
     * @return un mensaje indicando que la sesión ha sido invalidada si hay una sesión activa, de lo contrario, un mensaje indicando que no hay sesión activa para invalidar
     */
    @GetMapping("/invalidate")
    public String invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return "Session invalidated";
        }
        return "No active session to invalidate";
    }
}