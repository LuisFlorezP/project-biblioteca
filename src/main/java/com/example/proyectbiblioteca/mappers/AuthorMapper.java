package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.author.RequestAuthorDTO;
import com.example.proyectbiblioteca.dto.author.ResponseAuthorDTO;
import com.example.proyectbiblioteca.entities.Author;
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
    ResponseAuthorDTO authorToResponseAuthorDto(Author author);
    List<ResponseAuthorDTO> authorsToResponsesAuthorsDtos(List<Author> authors);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Author responseAuthorDtoToAuthor(ResponseAuthorDTO responseAuthorDTO);
    List<Author> responsesAuthorsDtosToAuthors(List<ResponseAuthorDTO> responseAuthorDTOS);

    @Mapping(source = "nacionalidad.id", target = "idNacionalidad")
    RequestAuthorDTO authorToRequestAuthorDto(Author author);
    List<RequestAuthorDTO> authorsToRequestsAuthorsDtos(List<Author> authors);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Author requestAuthorDtoToAuthor(RequestAuthorDTO requestAuthorDTO);
    List<Author> requestsAuthorsDtosToAuthors(List<RequestAuthorDTO> requestAuthorDTOS);
}
