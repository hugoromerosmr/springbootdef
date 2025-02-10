package org.example.letmalagaapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
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

    // Constructor
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

    public Reserva( ) {

    }
}
