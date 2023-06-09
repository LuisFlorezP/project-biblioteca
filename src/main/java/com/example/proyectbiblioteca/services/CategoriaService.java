package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.categoria.CategoriaDTO;
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

    public List<CategoriaDTO> getAllCategories() {
        return categoryMapper.toCategories(categoriaRepository.findAll());
    }

    public CategoriaDTO getCategory(Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> categoryMapper.toCategory(categoria))
                .orElse(null);

    }

    public CategoriaDTO saveCategory(Categoria categoria) {
        Optional<Categoria> categoriaNombre = categoriaRepository.findByNombre(categoria.getNombre());
        if (categoriaNombre.isPresent() || verificarDescripcionCategoria(categoria.getDescripcion().length())) {
            return null;
        }
        return categoryMapper.toCategory(categoriaRepository.save(categoria));
    }

    public CategoriaDTO updateCategory(Categoria categoria, Long id) {
        return  categoriaRepository.findById(id)
                .map(
                        data -> {
                            Optional<Categoria> search = categoriaRepository.findByNombre(categoria.getNombre());
                            if ((search.isPresent() && !search.get().getNombre().equals(categoria.getNombre())) || verificarDescripcionCategoria(categoria.getDescripcion().length())) {
                                return null;
                            }
                            data.setNombre(categoria.getNombre());
                            data.setDescripcion(categoria.getDescripcion());
                            return categoryMapper.toCategory(categoriaRepository.save(data));
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
