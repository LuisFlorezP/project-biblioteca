package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.editorial.ResponseEditorialDTO;
import com.example.proyectbiblioteca.entities.Editorial;
import com.example.proyectbiblioteca.mappers.PublishingHouseMapper;
import com.example.proyectbiblioteca.repositories.EditorialRepository;
import com.example.proyectbiblioteca.validations.GenerateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EditorialService extends GenerateValidation {

    @Autowired
    private EditorialRepository editorialRepository;
    @Autowired
    private PublishingHouseMapper publishingHouseMapper;

    public List<ResponseEditorialDTO> getAllEditorials() throws Exception {
        try {
            return publishingHouseMapper.toPublishingHouses(editorialRepository.findAll());
        } catch (Exception e) {
            throw new Exception("No se pudieron encontrar los registros de Editorial.");
        }
    }

    public ResponseEditorialDTO getEditorial(Long id) throws Exception {
        try {
            Optional<Editorial> editorial = editorialRepository.findById(id);
            if (editorial.isPresent()) {
                return publishingHouseMapper.toPublishingHouse(editorial.get());
            }
            throw new Exception("La editorial no ha sido encontrada.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEditorialDTO saveEditorial(Editorial editorial) throws Exception {
        try {
            Optional<Editorial> editorialNombre = editorialRepository.findByNombre(editorial.getNombre());
            if (editorialNombre.isPresent()) {
                throw new Exception("La editorial debe registrar un nombre único.");
            } else if (verificarNombre(editorial.getNombre())) {
                throw new Exception("La editorial debe registrar un nombre válido (entre 2 y 30 caracteres).");
            } else if (verificarDescripcionEditorial(editorial.getDescripcion())) {
                throw new Exception("La editorial debe registrar una descripción válida (hasta 300 caracteres).");
            }
            return publishingHouseMapper.toPublishingHouse(editorialRepository.save(editorial));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEditorialDTO updateEditorial(Editorial editorial, Long id) throws Exception {
        try {
            Optional<Editorial> search = editorialRepository.findById(id);
            if (search.isPresent()) {
                Optional<Editorial> editorialNombre = editorialRepository.findByNombre(editorial.getNombre());
                if (editorialNombre.isPresent() && !editorialNombre.get().getNombre().equals(editorial.getNombre())) {
                    throw new Exception("La editorial debe registrar un nombre único.");
                } else if (verificarNombre(editorial.getNombre())) {
                    throw new Exception("La editorial debe registrar un nombre válido (entre 2 y 30 caracteres).");
                } else if (verificarDescripcionEditorial(editorial.getDescripcion())) {
                    throw new Exception("La editorial debe registrar una descripción válida (hasta 300 caracteres).");
                }
                Editorial data = search.get();
                data.setNombre(editorial.getNombre());
                data.setDescripcion(editorial.getDescripcion());
                return publishingHouseMapper.toPublishingHouse(editorialRepository.save(data));
            }
            throw new Exception("La editorial no ha sido encontrado según el id brindado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteEditorial(Long id) throws Exception {
        try {
            if (editorialRepository.findById(id).isPresent()) {
                editorialRepository.deleteById(id);
                return;
            }
            throw new Exception("La editorial no ha sido encontrada, por ende no ha sido eliminada.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
