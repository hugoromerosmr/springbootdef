package org.example.letmalagaapp.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Getter
@Setter
@Document(collection = "alojamientos")
public class Alojamiento {

    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private double precio_minimo;
    private String moneda;
    private List<String> imagenes;


}
