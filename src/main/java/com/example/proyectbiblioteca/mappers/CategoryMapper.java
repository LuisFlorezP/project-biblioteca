package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.CategoriaDTO;
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
    CategoriaDTO toCategory(Categoria categoria);
    List<CategoriaDTO> toCategories(List<Categoria> categorias);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Categoria toCategoria(CategoriaDTO categoriaDTO);
    List<Categoria> toCategorias(List<CategoriaDTO> categories);
}
