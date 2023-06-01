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
        return categoriaRepository.findByNombre(categoria.getNombre())
                .map(
                        data -> {
                            if (verificarDescripcionCategoria(categoria.getDescripcion().length())) {
                                return Optional.<Categoria>empty();
                            }
                            return Optional.of(categoriaRepository.save(categoria));
                        }
                ).orElse(null);
    }

    public Categoria updateCategory(Categoria categoria, Long id) {
        Categoria search = categoriaRepository.findByNombre(categoria.getNombre()).get();
        if (search.getNombre().equals(categoria.getNombre()) || verificarDescripcionCategoria(categoria.getDescripcion().length())) {
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
