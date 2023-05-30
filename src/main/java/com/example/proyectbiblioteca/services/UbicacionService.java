package com.example.proyectbiblioteca.services;

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

    public List<Ubicacion> getAllLocations() {
        return ubicacionRepository.findAll();
    }

    public Optional<Ubicacion> getLocation(Long id) {
        return ubicacionRepository.findById(id);
    }

    public Optional<Ubicacion> saveLocation(Ubicacion ubicacion) {
        if (ubicacion.getPiso() == null || ubicacion.getSalon() == null || ubicacion.getEstante() == null) {
            return Optional.empty();
        }
        return Optional.of(ubicacionRepository.save(ubicacion));
    }

    public Ubicacion updateLocation(Ubicacion ubicacion, Long id) {
        if (ubicacion.getPiso() == null || ubicacion.getSalon() == null || ubicacion.getEstante() == null) {
            return null;
        }
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

    public boolean deleteLocation(Long id) {
        return ubicacionRepository.findById(id)
                .map(ubicacion -> {
                    ubicacionRepository.delete(ubicacion);
                    return true;
                })
                .orElse(false);
    }
}
