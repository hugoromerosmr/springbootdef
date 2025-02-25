package org.example.letmalagaapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaDTO {
    private String alojamientoId;
    private String nombreAlojamiento;
    private String imagenAlojamiento;
    private String fechaInicio;
    private String fechaFin;
    private double total;
    private String estado;

    // Otros getters y setters...
}