package org.example.letmalagaapp.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa un DTO (Data Transfer Object) para las reservas.
 */
@Getter
@Setter
public class ReservaDTO {
    /**
     * ID del alojamiento.
     */
    private String alojamientoId;

    /**
     * Nombre del alojamiento.
     */
    private String nombreAlojamiento;

    /**
     * Imagen del alojamiento.
     */
    private String imagenAlojamiento;

    /**
     * Fecha de inicio de la reserva.
     */
    private String fechaInicio;

    /**
     * Fecha de fin de la reserva.
     */
    private String fechaFin;

    /**
     * Total de la reserva.
     */
    private double total;

    /**
     * Estado de la reserva.
     */
    private String estado;

    // Otros getters y setters...
}