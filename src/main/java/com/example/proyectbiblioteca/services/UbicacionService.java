package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.entities.Editorial;
import com.example.proyectbiblioteca.entities.Ubicacion;
import com.example.proyectbiblioteca.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<Ubicacion> getAllUbicacion() {
        return ubicacionRepository.findAll();
    }

    public Optional<Ubicacion> getUbicacion(Long id) {
        return ubicacionRepository.findById(id);
    }

    public Optional<Ubicacion> saveUbicacion(Ubicacion ubicacion) {
        if (ubicacion.getPiso() == null || ubicacion.getSalon() == null || ubicacion.getEstante() == null) {
            return Optional.empty();
        }
        return Optional.of(ubicacionRepository.save(ubicacion));
    }

    public Ubicacion updateUbicacion(Ubicacion ubicacion, Long id) {
        return  ubicacionRepository.findById(id)
                .map(
                        data -> {
                            data.setPiso(ubicacion.getPiso());
                            data.setSalon(ubicacion.getSalon());
                            data.setEstante(ubicacion.getEstante());
                            return ubicacionRepository.save(data);
                        }
                ).orElse(null);
    }

    public boolean deleteUbicacion(Long id) {
        return ubicacionRepository.findById(id)
                .map(ubicacion -> {
                    ubicacionRepository.delete(ubicacion);
                    return true;
                })
                .orElse(false);
    }
}
