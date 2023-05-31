package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.entities.Categoria;
import com.example.proyectbiblioteca.repositories.CategoriaRepository;
import com.example.proyectbiblioteca.validations.GenerateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService extends GenerateValidation {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategories() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getCategory(Long id) {
        return categoriaRepository.findById(id);
    }

    public Optional<Categoria> saveCategory(Categoria categoria) {
        if (verificarNombreDescripcion(categoriaRepository.findByNombre(categoria.getNombre()).isPresent(), categoria.getDescripcion().length())) {
            return Optional.empty();
        }
        return Optional.of(categoriaRepository.save(categoria));
    }

    public Categoria updateCategory(Categoria categoria, Long id) {
        if (verificarNombreDescripcion(!categoriaRepository.findByNombre(categoria.getNombre()).get().getNombre().equals(categoria.getNombre()), categoria.getDescripcion().length())) {
            return null;
        }
        return  categoriaRepository.findById(id)
                .map(
                        data -> {
                            data.setNombre(categoria.getNombre());
                            data.setDescripcion(categoria.getDescripcion());
                            return categoriaRepository.save(data);
                        }
                ).orElse(null);
    }

    public boolean deleteCategory(Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoriaRepository.delete(categoria);
                    return true;
                })
                .orElse(false);
    }
}
