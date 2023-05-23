package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.entities.Editorial;
import com.example.proyectbiblioteca.repositories.EditorialRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class EditorialService {

    private EditorialRepository editorialRepository;

    public List<Editorial> getAllEditorial() {
        return editorialRepository.findAll();
    }

    public Optional<Editorial> getEditorial(String nombre) {
        return editorialRepository.findById(nombre);
    }

    public Editorial saveEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    public Editorial updateEditorial(Editorial editorial, String nombre) {
        return  editorialRepository.findById(nombre)
                .map(
                    data -> {
                        data.setNombre(editorial.getNombre());
                        data.setDescripcion(editorial.getDescripcion());
                        return editorialRepository.save(data);
                    }
                ).orElse(null);
    }

    public boolean deleteEditorial(String nombre) {
        if (editorialRepository.findById(nombre).equals(Optional.empty())) {
            return false;
        }
        editorialRepository.deleteById(nombre);
        return true;
    }
}
