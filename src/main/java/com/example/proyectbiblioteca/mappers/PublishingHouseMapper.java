package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.editorial.EditorialDTO;
import com.example.proyectbiblioteca.entities.Editorial;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublishingHouseMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description")
    })
    EditorialDTO toPublishingHouse(Editorial editorial);
    List<EditorialDTO> toPublishingHouses(List<Editorial> editorials);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Editorial toEditorial(EditorialDTO editorialDTO);
    List<Editorial> toEditorials(List<EditorialDTO> editorialDTOS);
}
