package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.location.RequestLocationDTO;
import com.example.proyectbiblioteca.dto.location.ResponseLocationDTO;
import com.example.proyectbiblioteca.entities.Location;
import com.example.proyectbiblioteca.mappers.LocationMapper;
import com.example.proyectbiblioteca.repositories.LocationRepository;
import com.example.proyectbiblioteca.validations.LocationValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService extends LocationValidations {

    @Autowired
    private LocationRepository repository;
    @Autowired
    private LocationMapper mapper;

    public List<ResponseLocationDTO> getAllLocations() throws Exception {
        try {
            return mapper.locationsToResponseLocationsDtos(repository.findAll());
        } catch (Exception e) {
            throw new Exception("The Location entity records could not be read.");
        }
    }

    public ResponseLocationDTO getLocation(Long id) throws Exception {
        try {
            Optional<Location> location = repository.findById(id);
            if (locationPresent(location)) {
                return mapper.locationToResponseLocationDto(location.get());
            }
            throw new Exception("The Location entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public RequestLocationDTO saveLocation(Location location) throws Exception {
        try {
            if (attributePresent(location.getPiso())) {
                throw new Exception("The location must register floor.");
            } else if (attributePresent(location.getSalon())) {
                throw new Exception("The location must register lounge.");
            } else if (attributePresent(location.getEstante())) {
                throw new Exception("The location must register shelf.");
            }
            return mapper.locationToRequestLocationDto(repository.save(location));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public RequestLocationDTO updateLocation(Location location, Long id) throws Exception {
        try {
            Optional<Location> search = repository.findById(id);
            if (locationPresent(search)) {
                if (attributePresent(location.getPiso())) {
                    throw new Exception("The location must register floor.");
                } else if (attributePresent(location.getSalon())) {
                    throw new Exception("The location must register lounge.");
                } else if (attributePresent(location.getEstante())) {
                    throw new Exception("The location must register shelf.");
                }
                Location data = search.get();
                data.setPiso(location.getPiso());
                data.setSalon(location.getSalon());
                data.setEstante(location.getEstante());
                return mapper.locationToRequestLocationDto(repository.save(data));
            }
            throw new Exception("The Location entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteLocation(Long id) throws Exception {
        try {
            if (locationPresent(repository.findById(id))) {
                repository.deleteById(id);
                return;
            }
            throw new Exception("The Location entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
