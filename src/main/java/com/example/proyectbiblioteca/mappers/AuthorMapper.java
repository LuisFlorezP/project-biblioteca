package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.AutorDTO;
import com.example.proyectbiblioteca.entities.Autor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellido", target = "lastName"),
            @Mapping(source = "pseudonimo", target = "pseudonym"),
            @Mapping(source = "nacionalidad", target = "nationality")
    })
    AutorDTO toAuthor(Autor autor);
    List<AutorDTO> toAuthors(List<Autor> autors);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Autor toAutor(AutorDTO autorDTO);
    List<Autor> toAutors(List<AutorDTO> autorDTOS);
}
