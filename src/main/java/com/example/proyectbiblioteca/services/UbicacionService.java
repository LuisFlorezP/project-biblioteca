package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.ubicacion.ResponseUbicacionDTO;
import com.example.proyectbiblioteca.entities.Ubicacion;
import com.example.proyectbiblioteca.mappers.LocationMapper;
import com.example.proyectbiblioteca.repositories.UbicacionRepository;
import com.example.proyectbiblioteca.validations.UbicacionValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UbicacionService extends UbicacionValidations {

    @Autowired
    private UbicacionRepository ubicacionRepository;
    @Autowired
    private LocationMapper locationMapper;

    public List<ResponseUbicacionDTO> getAllLocations() throws Exception {
        try {
            return locationMapper.toLocations(ubicacionRepository.findAll());
        } catch (Exception e) {
            throw new Exception("No se pudieron encontrar los registros de Ubicación.");
        }
    }

    public ResponseUbicacionDTO getLocation(Long id) throws Exception {
        try {
            Optional<Ubicacion> ubicacion = ubicacionRepository.findById(id);
            if (ubicacionPresente(ubicacion)) {
                return locationMapper.toLocation(ubicacion.get());
            }
            throw new Exception("La ubicación no ha sido encontrada.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseUbicacionDTO saveLocation(Ubicacion ubicacion) throws Exception {
        try {
            if (atributoPresente(ubicacion.getPiso())) {
                throw new Exception("La ubicación debe registrar un piso.");
            } else if (atributoPresente(ubicacion.getSalon())) {
                throw new Exception("La ubicación debe registrar un salón.");
            } else if (atributoPresente(ubicacion.getEstante())) {
                throw new Exception("La ubicación debe registrar un estante.");
            }
            return locationMapper.toLocation(ubicacionRepository.save(ubicacion));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseUbicacionDTO updateLocation(Ubicacion ubicacion, Long id) throws Exception {
        try {
            Optional<Ubicacion> search = ubicacionRepository.findById(id);
            if (ubicacionPresente(search)) {
                if (atributoPresente(ubicacion.getPiso())) {
                    throw new Exception("La ubicación debe registrar un piso.");
                } else if (atributoPresente(ubicacion.getSalon())) {
                    throw new Exception("La ubicación debe registrar un salón.");
                } else if (atributoPresente(ubicacion.getEstante())) {
                    throw new Exception("La ubicación debe registrar un estante.");
                }
                Ubicacion data = search.get();
                data.setPiso(ubicacion.getPiso());
                data.setSalon(ubicacion.getSalon());
                data.setEstante(ubicacion.getEstante());
                return locationMapper.toLocation(data);
            }
            throw new Exception("La ubicación no ha sido encontrado según el id brindado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteLocation(Long id) throws Exception {
        try {
            if (ubicacionPresente(ubicacionRepository.findById(id))) {
                ubicacionRepository.deleteById(id);
                return;
            }
            throw new Exception("La ubicación no ha sido encontrada, por ende no ha sido eliminada.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
