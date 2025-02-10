package org.example.letmalagaapp.services;

import org.example.letmalagaapp.models.Alojamiento;
import org.example.letmalagaapp.repositories.AlojamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlojamientosService {

    @Autowired
    private AlojamientoRepository alojamientoRepository;

    public AlojamientosService(AlojamientoRepository alojamientoRepository) {
        this.alojamientoRepository = alojamientoRepository;
    }


    // Obtener todos los alojamientos
    public List<Alojamiento> getAllAlojamientos() {
        return alojamientoRepository.findAll();  // Devuelve todos los alojamientos
    }

    // Obtener alojamientos por nombre
    public List<Alojamiento> getAlojamientosByNombre(String nombre) {
        return alojamientoRepository.findByNombreContaining(nombre);  // Busca por nombre
    }

    // Otros métodos como guardar, actualizar, eliminar, etc.
}
