package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.ubicacion.ErrorLocationDTO;
import com.example.proyectbiblioteca.dto.ubicacion.LocationDTO;
import com.example.proyectbiblioteca.dto.ubicacion.RequestLocationDTO;
import com.example.proyectbiblioteca.dto.ubicacion.ResponseLocationDTO;
import com.example.proyectbiblioteca.mappers.LocationMapper;
import com.example.proyectbiblioteca.services.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationRestController {

    @Autowired
    private LocationService service;
    @Autowired
    private LocationMapper mapper;

    @Operation(summary = "Read all locations stored in database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Records for the Location entity have been found.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseLocationDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "The Location entity records could not be read.",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        try {
            return new ResponseEntity<>(new ArrayList<>(service.getAllLocations()), HttpStatus.OK);
        } catch (Exception e) {
            List<LocationDTO> locationDTOS = new ArrayList<>();
            locationDTOS.add(new ErrorLocationDTO(e.getMessage()));
            return new ResponseEntity<>(locationDTOS, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Read through the entered id an location stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The Location entity record has been found.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseLocationDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "The Location entity record was not found.",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getLocation(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorLocationDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create the location entered to save it in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "The Location entered has been created and saved.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RequestLocationDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The Location entered has not been created and saved: (case)",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<LocationDTO> saveLocation(@RequestBody RequestLocationDTO location) {
        try {
            return new ResponseEntity<>(service.saveLocation(mapper.requestLocationDtoToLocation(location)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorLocationDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update the location entered if found by id to update save in database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "The Location entered has been updated and saved.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RequestLocationDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The Location entered has not been updated and saved: (case)",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> updateLocation(@RequestBody RequestLocationDTO location, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.updateLocation(mapper.requestLocationDtoToLocation(location), id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorLocationDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete by means of the entered id an location stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "The Location entered has been removed.",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "The Location entered has not been removed.",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<LocationDTO> deleteLocation(@PathVariable Long id) {
        try {
            service.deleteLocation(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorLocationDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
