package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.categoria.ResponseCategoryDTO;
import com.example.proyectbiblioteca.entities.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description")
    })
    ResponseCategoryDTO categoryToResponseCategoryDto(Category category);
    List<ResponseCategoryDTO> categoriesToResponseCategoriesDtos(List<Category> categories);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Category responseCategoryDtoToCategory(ResponseCategoryDTO responseCategoryDTO);
    List<Category> responseCategoriesDtoToCategories(List<ResponseCategoryDTO> responseCategoryDTOS);
}
