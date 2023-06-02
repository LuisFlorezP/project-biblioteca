package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.Location;
import com.example.proyectbiblioteca.entities.Ubicacion;
import com.example.proyectbiblioteca.mappers.LocationMapper;
import com.example.proyectbiblioteca.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Ubicacion> ubicacion = ubicacionRepository.findById(id);
        return ubicacion.map(value -> locationMapper.toLocation(value))
                .orElse(null);
    }

    public Optional<Location> saveLocation(Location location) {
        if (location.getFloor() == null || location.getLounge() == null || location.getShelf() == null) {
            return Optional.empty();
        }
        Ubicacion ubicacion = locationMapper.toUbicacion(location);
        return Optional.of(locationMapper.toLocation(ubicacionRepository.save(ubicacion)));
    }

    public Location updateLocation(Location location, Long id) {
        if (location.getFloor() == null || location.getLounge() == null || location.getShelf() == null) {
            return null;
        }
        return  ubicacionRepository.findById(id)
                .map(
                        data -> {
                            data.setPiso(location.getFloor());
                            data.setSalon(location.getLounge());
                            data.setEstante(location.getShelf());
                            return locationMapper.toLocation(ubicacionRepository.save(data));
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
