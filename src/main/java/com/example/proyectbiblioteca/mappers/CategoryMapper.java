package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.categoria.ResponseCategoriaDTO;
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
    ResponseCategoriaDTO toCategory(Categoria categoria);
    List<ResponseCategoriaDTO> toCategories(List<Categoria> categorias);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Categoria toCategoria(ResponseCategoriaDTO responseCategoriaDTO);
    List<Categoria> toCategorias(List<ResponseCategoriaDTO> categories);
}
