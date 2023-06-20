package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.ubicacion.RequestLocationDTO;
import com.example.proyectbiblioteca.dto.ubicacion.ResponseLocationDTO;
import com.example.proyectbiblioteca.entities.Location;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mappings({
            @Mapping(source = "piso", target = "floor"),
            @Mapping(source = "salon", target = "lounge"),
            @Mapping(source = "estante", target = "shelf")
    })
    ResponseLocationDTO locationToResponseLocationDto(Location location);
    List<ResponseLocationDTO> locationsToResponseLocationsDtos(List<Location> locations);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Location responseLocationDtoToLocation(ResponseLocationDTO responseLocationDTO);
    List<Location> responseLocationsDtosToLocations(List<ResponseLocationDTO> responseLocationDTOS);

    RequestLocationDTO locationToRequestLocationDto(Location location);
    List<ResponseLocationDTO> locationsToRequestLocationsDtos(List<Location> locations);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Location requestLocationDtoToLocation(RequestLocationDTO requestLocationDTO);
    List<Location> requestLocationsDtosToLocations(List<RequestLocationDTO> requestLocationDTOS);
}
