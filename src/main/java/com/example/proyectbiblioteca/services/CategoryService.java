package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.categoria.RequestCategoryDTO;
import com.example.proyectbiblioteca.dto.categoria.ResponseCategoryDTO;
import com.example.proyectbiblioteca.entities.Category;
import com.example.proyectbiblioteca.mappers.CategoryMapper;
import com.example.proyectbiblioteca.repositories.CategoryRepository;
import com.example.proyectbiblioteca.validations.CategoryValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService extends CategoryValidations {

    @Autowired
    private CategoryRepository repository;
    @Autowired
    private CategoryMapper mapper;

    public List<ResponseCategoryDTO> getAllCategories() throws Exception {
        try {
            return mapper.categoriesToResponseCategoriesDtos(repository.findAll());
        } catch (Exception e) {
            throw new Exception("The Category entity records could not be read.");
        }
    }

    public ResponseCategoryDTO getCategory(Long id) throws Exception {
        try {
            Optional<Category> category = repository.findById(id);
            if (categoryPresent(category)) {
                return mapper.categoryToResponseCategoryDto(category.get());
            }
            throw new Exception("The Category entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public RequestCategoryDTO saveCategory(Category category) throws Exception {
        try {
            if (verifyNombre(category.getNombre())) {
                throw new Exception("The category must register name.");
            }
            Optional<Category> categoryNombre = repository.findByNombre(category.getNombre());
            if (categoryPresent(categoryNombre)) {
                throw new Exception("The category must register unique name.");
            }
            if (verifyDescripcionCategory(category.getDescripcion())) {
                throw new Exception("The category must register brief description (until 255 characters).");
            }
            return mapper.categoryToRequestCategoryDto(repository.save(category));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public RequestCategoryDTO updateCategory(Category category, Long id) throws Exception {
        try {
            Optional<Category> search = repository.findById(id);
            if (categoryPresent(search)) {
                Optional<Category> categoryNombre = repository.findByNombre(category.getNombre());
                if (categoryPresent(categoryNombre) && nombrePresentEqualDifferent(categoryNombre.get().getNombre(), search.get().getNombre())) {
                    throw new Exception("The category must register unique name.");
                }
                if (verifyDescripcionCategory(category.getDescripcion())) {
                    throw new Exception("The category must register brief description (until 255 characters).");
                }
                Category data = search.get();
                data.setNombre(category.getNombre());
                data.setDescripcion(category.getDescripcion());
                return mapper.categoryToRequestCategoryDto(repository.save(data));
            }
            throw new Exception("The Category entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteCategory(Long id) throws Exception {
        try {
            if (categoryPresent(repository.findById(id))) {
                repository.deleteById(id);
                return;
            }
            throw new Exception("The Category entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
