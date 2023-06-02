package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.Category;
import com.example.proyectbiblioteca.entities.Categoria;
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
    Category toCategory(Categoria categoria);
    List<Category> toCategories(List<Categoria> categorias);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Categoria toCategoria(Category category);
    List<Categoria> toCategorias(List<Category> categories);
}
