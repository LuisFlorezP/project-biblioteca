package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.publishinghouse.RequestPublishingHouseDTO;
import com.example.proyectbiblioteca.dto.publishinghouse.ResponsePublishingHouseDTO;
import com.example.proyectbiblioteca.entities.PublishingHouse;
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
    ResponsePublishingHouseDTO publishingHouseToResponsePublishingHouseDto(PublishingHouse publishingHouse);
    List<ResponsePublishingHouseDTO> publishingHousesToResponsePublishingHousesDtos(List<PublishingHouse> publishingHouses);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    PublishingHouse responsePublishingHouseDtoToPublishingHouse(ResponsePublishingHouseDTO responsePublishingHouseDTO);
    List<PublishingHouse> responsePublishingHousesDtosToPublishingHouses(List<ResponsePublishingHouseDTO> responsePublishingHouseDTOS);

    RequestPublishingHouseDTO publishingHouseToRequestPublishingHouseDto(PublishingHouse publishingHouse);
    List<RequestPublishingHouseDTO> publishingHousesToRequestPublishingHousesDtos(List<PublishingHouse> publishingHouses);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    PublishingHouse requestPublishingHouseDtoToPublishingHouse(RequestPublishingHouseDTO requestPublishingHouseDTO);
    List<PublishingHouse> requestPublishingHousesDtosToPublishingHouses(List<RequestPublishingHouseDTO> requestPublishingHouseDTOS);
}
