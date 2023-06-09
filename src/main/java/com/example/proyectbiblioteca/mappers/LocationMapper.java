package com.example.proyectbiblioteca.mappers;

import com.example.proyectbiblioteca.dto.UbicacionDTO;
import com.example.proyectbiblioteca.entities.Ubicacion;
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
    UbicacionDTO toLocation(Ubicacion ubicacion);
    List<UbicacionDTO> toLocations(List<Ubicacion> ubicacions);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Ubicacion toUbicacion(UbicacionDTO ubicacionDTO);
    List<Ubicacion> toUbications(List<UbicacionDTO> ubicacionDTOS);
}
