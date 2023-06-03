package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.Category;
import com.example.proyectbiblioteca.entities.Categoria;
import com.example.proyectbiblioteca.mappers.CategoryMapper;
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
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getAllCategories() {
        return categoryMapper.toCategories(categoriaRepository.findAll());
    }

    public Category getCategory(Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> categoryMapper.toCategory(categoria))
                .orElse(null);

    }

    public Category saveCategory(Category category) {
        Optional<Categoria> categoriaNombre = categoriaRepository.findByNombre(category.getName());
        if (categoriaNombre.isPresent() || verificarDescripcionCategoria(category.getDescription().length())) {
            return null;
        }
        Categoria data = categoriaRepository.save(categoryMapper.toCategoria(category));
        return categoryMapper.toCategory(data);
    }

    public Category updateCategory(Category category, Long id) {
        return  categoriaRepository.findById(id)
                .map(
                        data -> {
                            Optional<Categoria> search = categoriaRepository.findByNombre(category.getName());
                            if ((search.isPresent() && !search.get().getNombre().equals(category.getName())) || verificarDescripcionCategoria(category.getDescription().length())) {
                                return null;
                            }
                            data.setNombre(category.getName());
                            data.setDescripcion(category.getDescription());
                            Categoria categoria = categoriaRepository.save(data);
                            return categoryMapper.toCategory(categoria);
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
