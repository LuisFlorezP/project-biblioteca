package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.Author;
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
            @Mapping(source = "nacionalidad.id", target = "nationality")
    })
    Author toAuthor(Autor autor);
    List<Author> toAuthors(List<Autor> autors);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "nacionalidad", ignore = true)
    })
    Autor toAutor(Author author);
    List<Autor> toAutors(List<Author> authors);
}
