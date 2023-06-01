package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.entities.Editorial;
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

    public List<Editorial> getAllEditorial() {
        return editorialRepository.findAll();
    }

    public Optional<Editorial> getEditorial(Long id) {
        return editorialRepository.findById(id);
    }

    public Optional<Editorial> saveEditorial(Editorial editorial) {
        if (editorialRepository.findByNombre(editorial.getNombre()).isPresent() || verificarNombre(editorial.getNombre()) || verificarDescripcionEditorial(editorial.getDescripcion())) {
            return Optional.empty();
        }
        return Optional.of(editorialRepository.save(editorial));
    }

    public Editorial updateEditorial(Editorial editorial, Long id) {
        return  editorialRepository.findById(id)
                .map(
                    data -> {
                        if (editorialRepository.findByNombre(editorial.getNombre()).get().equals(editorial) || verificarNombre(editorial.getNombre()) || verificarDescripcionEditorial(editorial.getDescripcion())) {

                        }
                        data.setNombre(editorial.getNombre());
                        data.setDescripcion(editorial.getDescripcion());
                        return editorialRepository.save(data);
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
