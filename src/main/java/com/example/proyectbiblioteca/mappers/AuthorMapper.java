package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.autor.ResponseAutorDTO;
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
    ResponseAutorDTO toAuthor(Autor autor);
    List<ResponseAutorDTO> toAuthors(List<Autor> autors);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Autor toAutor(ResponseAutorDTO responseAutorDTO);
    List<Autor> toAutors(List<ResponseAutorDTO> responseAutorDTOS);
}
