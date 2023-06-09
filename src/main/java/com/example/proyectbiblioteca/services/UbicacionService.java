package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.ubicacion.ResponseUbicacionDTO;
import com.example.proyectbiblioteca.entities.Ubicacion;
import com.example.proyectbiblioteca.mappers.LocationMapper;
import com.example.proyectbiblioteca.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;
    @Autowired
    private LocationMapper locationMapper;

    public List<ResponseUbicacionDTO> getAllLocations() {
        return locationMapper.toLocations(ubicacionRepository.findAll());
    }

    public ResponseUbicacionDTO getLocation(Long id) {
        return ubicacionRepository.findById(id)
                .map(ubicacion -> locationMapper.toLocation(ubicacion))
                .orElse(null);
    }

    public ResponseUbicacionDTO saveLocation(Ubicacion ubicacion) {
        if (ubicacion.getPiso() == null || ubicacion.getSalon() == null || ubicacion.getEstante() == null) {
            return null;
        }
        return locationMapper.toLocation(ubicacionRepository.save(ubicacion));
    }

    public ResponseUbicacionDTO updateLocation(Ubicacion ubicacion, Long id) {
        return  ubicacionRepository.findById(id)
                .map(
                        data -> {
                            if (ubicacion.getPiso() == null || ubicacion.getSalon() == null || ubicacion.getEstante() == null) {
                                return null;
                            }
                            data.setPiso(ubicacion.getPiso());
                            data.setSalon(ubicacion.getSalon());
                            data.setEstante(ubicacion.getEstante());
                            return locationMapper.toLocation(ubicacionRepository.save(ubicacion));
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
