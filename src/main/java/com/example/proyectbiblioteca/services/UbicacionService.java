package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.Location;
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

    public List<Location> getAllLocations() {
        return locationMapper.toLocations(ubicacionRepository.findAll());
    }

    public Location getLocation(Long id) {
        return ubicacionRepository.findById(id)
                .map(ubicacion -> locationMapper.toLocation(ubicacion))
                .orElse(null);
    }

    public Location saveLocation(Location location) {
        if (location.getFloor() == null || location.getLounge() == null || location.getShelf() == null) {
            return null;
        }
        Ubicacion ubicacion = ubicacionRepository.save(locationMapper.toUbicacion(location));
        return locationMapper.toLocation(ubicacion);
    }

    public Location updateLocation(Location location, Long id) {
        return  ubicacionRepository.findById(id)
                .map(
                        data -> {
                            if (location.getFloor() == null || location.getLounge() == null || location.getShelf() == null) {
                                return null;
                            }
                            data.setPiso(location.getFloor());
                            data.setSalon(location.getLounge());
                            data.setEstante(location.getShelf());
                            Ubicacion ubicacion = ubicacionRepository.save(data);
                            return locationMapper.toLocation(ubicacion);
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
