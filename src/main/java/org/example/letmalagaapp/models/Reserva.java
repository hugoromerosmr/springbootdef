package org.example.letmalagaapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Representa una reserva en la aplicación.
 */
@Getter
@Setter
@Document(collection = "reservas")
public class Reserva {

    @Id
    private String id;
    private String usuarioId;
    private String alojamientoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int personas;
    private double total;
    private String moneda;
    private String estado;
    private String metodoPago;

    /**
     * Constructor con parámetros.
     *
     * @param id el ID de la reserva
     * @param usuarioId el ID del usuario que realiza la reserva
     * @param alojamientoId el ID del alojamiento reservado
     * @param fechaInicio la fecha de inicio de la reserva
     * @param fechaFin la fecha de fin de la reserva
     * @param personas el número de personas para la reserva
     * @param total el costo total de la reserva
     * @param moneda la moneda utilizada para el pago
     * @param estado el estado de la reserva
     * @param metodoPago el método de pago utilizado
     */
    public Reserva(String id, String usuarioId, String alojamientoId, LocalDate fechaInicio, LocalDate fechaFin, int personas, double total, String moneda, String estado, String metodoPago) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.alojamientoId = alojamientoId;
        this.fechaInicio = (fechaInicio != null) ? fechaInicio : LocalDate.now(); // Evitar null
        this.fechaFin = (fechaFin != null) ? fechaFin : this.fechaInicio.plusDays(1); // Evitar null
        this.personas = personas;
        this.total = total;
        this.moneda = moneda;
        this.estado = estado;
        this.metodoPago = metodoPago;
    }

    /**
     * Constructor por defecto.
     */
    public Reserva() {

    }
}