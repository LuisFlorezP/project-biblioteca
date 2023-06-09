package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.editorial.EditorialDTO;
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

    public List<EditorialDTO> getAllEditorial() {
        return publishingHouseMapper.toPublishingHouses(editorialRepository.findAll());
    }

    public EditorialDTO getEditorial(Long id) {
        return editorialRepository.findById(id)
                .map(editorial -> publishingHouseMapper.toPublishingHouse(editorial))
                .orElse(null);
    }

    public EditorialDTO saveEditorial(Editorial editorial) {
        Optional<Editorial> editorialNombre = editorialRepository.findByNombre(editorial.getNombre());
        if (editorialNombre.isPresent() || verificarNombre(editorial.getNombre()) || verificarDescripcionEditorial(editorial.getDescripcion())) {
            return null;
        }
        return publishingHouseMapper.toPublishingHouse(editorialRepository.save(editorial));
    }

    public EditorialDTO updateEditorial(Editorial editorial, Long id) {
        return  editorialRepository.findById(id)
                .map(
                        data -> {
                            Optional<Editorial> editorialNombre = editorialRepository.findByNombre(editorial.getNombre());
                            if ((editorialNombre.isPresent() && !editorialNombre.get().getNombre().equals(editorial.getNombre())) || verificarNombre(editorial.getNombre()) || verificarDescripcionEditorial(editorial.getDescripcion())) {
                                return null;
                            }
                            data.setNombre(editorial.getNombre());
                            data.setDescripcion(editorial.getDescripcion());
                            return publishingHouseMapper.toPublishingHouse(editorialRepository.save(data));
                        }
                ).orElse(null);
    }

    public boolean deleteEditorial(Long id) {
        if (editorialRepository.findById(id).equals(Optional.empty())) {
            return false;
        }
        editorialRepository.deleteById(id);
        return true;
    }
}
