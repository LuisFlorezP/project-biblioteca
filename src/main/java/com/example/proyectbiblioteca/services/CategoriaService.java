package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.categoria.ResponseCategoriaDTO;
import com.example.proyectbiblioteca.entities.Categoria;
import com.example.proyectbiblioteca.mappers.CategoryMapper;
import com.example.proyectbiblioteca.repositories.CategoriaRepository;
import com.example.proyectbiblioteca.validations.CategoriaValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService extends CategoriaValidations {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    public List<ResponseCategoriaDTO> getAllCategories() throws Exception {
        try {
            return categoryMapper.toCategories(categoriaRepository.findAll());
        } catch (Exception e) {
            throw new Exception("No se pudieron encontrar los registros de Categoria.");
        }
    }

    public ResponseCategoriaDTO getCategory(Long id) throws Exception {
        try {
            Optional<Categoria> categoria = categoriaRepository.findById(id);
            if (categoriaPresente(categoria)) {
                return categoryMapper.toCategory(categoria.get());
            }
            throw new Exception("La categoria no ha sido encontrada.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseCategoriaDTO saveCategory(Categoria categoria) throws Exception {
        try {
            if (verificarNombre(categoria.getNombre())) {
                throw new Exception("La categoria debe registrar un nombre.");
            }
            Optional<Categoria> categoriaNombre = categoriaRepository.findByNombre(categoria.getNombre());
            if (categoriaPresente(categoriaNombre)) {
                throw new Exception("La categoria debe registrar un nombre único.");
            }
            if (verificarDescripcionCategoria(categoria.getDescripcion())) {
                throw new Exception("La categoria debe registrar una descripción breve (hasta 255 caracteres).");
            }
            return categoryMapper.toCategory(categoriaRepository.save(categoria));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseCategoriaDTO updateCategory(Categoria categoria, Long id) throws Exception {
        try {
            Optional<Categoria> search = categoriaRepository.findById(id);
            if (categoriaPresente(search)) {
                Optional<Categoria> categoriaNombre = categoriaRepository.findByNombre(categoria.getNombre());
                if (categoriaPresente(categoriaNombre) && nombrePresenteIgualDiferente(categoriaNombre.get(), search.get())) {
                    throw new Exception("La categoria debe registrar un nombre único.");
                }
                if (verificarDescripcionCategoria(categoria.getDescripcion())) {
                    throw new Exception("La categoria debe registrar una descripción breve (hasta 255 caracteres).");
                }
                Categoria data = search.get();
                data.setNombre(categoria.getNombre());
                data.setDescripcion(categoria.getDescripcion());
                return categoryMapper.toCategory(categoriaRepository.save(data));
            }
            throw new Exception("La categoria no ha sido encontrado según el id brindado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteCategory(Long id) throws Exception {
        try {
            if (categoriaPresente(categoriaRepository.findById(id))) {
                categoriaRepository.deleteById(id);
                return;
            }
            throw new Exception("La categoria no ha sido encontrada, por ende no ha sido eliminada.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
