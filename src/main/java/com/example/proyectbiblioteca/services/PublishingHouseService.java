package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.editorial.RequestPublishingHouseDTO;
import com.example.proyectbiblioteca.dto.editorial.ResponsePublishingHouseDTO;
import com.example.proyectbiblioteca.entities.PublishingHouse;
import com.example.proyectbiblioteca.mappers.PublishingHouseMapper;
import com.example.proyectbiblioteca.repositories.PublishingHouseRepository;
import com.example.proyectbiblioteca.validations.PublishingHouseValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PublishingHouseService extends PublishingHouseValidations {

    @Autowired
    private PublishingHouseRepository repository;
    @Autowired
    private PublishingHouseMapper mapper;

    public List<ResponsePublishingHouseDTO> getAllPublishingHouses() throws Exception {
        try {
            return mapper.publishingHousesToResponsePublishingHousesDtos(repository.findAll());
        } catch (Exception e) {
            throw new Exception("The PublishingHouse entity records could not be read.");
        }
    }

    public ResponsePublishingHouseDTO getPublishingHouse(Long id) throws Exception {
        try {
            Optional<PublishingHouse> publishingHouse = repository.findById(id);
            if (publishingHousePresent(publishingHouse)) {
                return mapper.publishingHouseToResponsePublishingHouseDto(publishingHouse.get());
            }
            throw new Exception("The PublishingHouse entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public RequestPublishingHouseDTO savePublishingHouse(PublishingHouse publishingHouse) throws Exception {
        try {
            Optional<PublishingHouse> publishingHouseNombre = repository.findByNombre(publishingHouse.getNombre());
            if (publishingHousePresent(publishingHouseNombre)) {
                throw new Exception("The category must register unique name.");
            } else if (verifyNombre(publishingHouse.getNombre())) {
                throw new Exception("The category must register valid name (between 2 and 30 characters).");
            } else if (verifyDescripcionEditorial(publishingHouse.getDescripcion())) {
                throw new Exception("The category must register valid description (until 300 characters).");
            }
            return mapper.publishingHouseToRequestPublishingHouseDto(repository.save(publishingHouse));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public RequestPublishingHouseDTO updatePublishingHouse(PublishingHouse publishingHouse, Long id) throws Exception {
        try {
            Optional<PublishingHouse> search = repository.findById(id);
            if (publishingHousePresent(search)) {
                Optional<PublishingHouse> publishingHouseNombre = repository.findByNombre(publishingHouse.getNombre());
                if (publishingHousePresent(publishingHouseNombre) && nombrePresentEqualDifferent(publishingHouseNombre.get().getNombre(), search.get().getNombre())) {
                    throw new Exception("The category must register unique name.");
                } else if (verifyNombre(publishingHouse.getNombre())) {
                    throw new Exception("The category must register valid name (between 2 and 30 characters).");
                } else if (verifyDescripcionEditorial(publishingHouse.getDescripcion())) {
                    throw new Exception("he category must register valid description (until 300 characters).");
                }
                PublishingHouse data = search.get();
                data.setNombre(publishingHouse.getNombre());
                data.setDescripcion(publishingHouse.getDescripcion());
                return mapper.publishingHouseToRequestPublishingHouseDto(repository.save(data));
            }
            throw new Exception("The PublishingHouse entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deletePublishingHouse(Long id) throws Exception {
        try {
            if (publishingHousePresent(repository.findById(id))) {
                repository.deleteById(id);
                return;
            }
            throw new Exception("The PublishingHouse entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
