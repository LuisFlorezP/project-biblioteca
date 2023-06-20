package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.publishinghouse.ErrorPublishingHouseDTO;
import com.example.proyectbiblioteca.dto.publishinghouse.PublishingHouseDTO;
import com.example.proyectbiblioteca.dto.publishinghouse.RequestPublishingHouseDTO;
import com.example.proyectbiblioteca.dto.publishinghouse.ResponsePublishingHouseDTO;
import com.example.proyectbiblioteca.mappers.PublishingHouseMapper;
import com.example.proyectbiblioteca.services.PublishingHouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/publishingHouses")
@Tag(name = "Services of Publishing Houses", description = "Methods to be able to create, read, update and delete (CRUD) services corresponding to the PublishingHouse entity.")
public class PublishingHouseRestController {

    @Autowired
    private PublishingHouseService service;
    @Autowired
    private PublishingHouseMapper mapper;

    @Operation(summary = "Read all publishing houses stored in database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Records for the PublishingHouse entity have been found.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponsePublishingHouseDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "The PublishingHouse entity records could not be read.",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<PublishingHouseDTO>> getAllPublishingHouses() {
        try {
            return ResponseEntity.ok(new ArrayList<>(service.getAllPublishingHouses()));
        } catch (Exception e) {
            List<PublishingHouseDTO> publishingHouseDTOS = new ArrayList<>();
            publishingHouseDTOS.add(new ErrorPublishingHouseDTO(e.getMessage()));
            return new ResponseEntity<>(publishingHouseDTOS, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Read through the entered id an publishing house stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The PublishingHouse entity record has been found.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponsePublishingHouseDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "The PublishingHouse entity record was not found.",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PublishingHouseDTO> getPublishingHouse(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getPublishingHouse(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorPublishingHouseDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create the publishing house entered to save it in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "The PublishingHouse entered has been created and saved.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RequestPublishingHouseDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The PublishingHouse entered has not been created and saved: (case)",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<PublishingHouseDTO> savePublishingHouse(@RequestBody RequestPublishingHouseDTO publishingHouse) {
        try {
            return new ResponseEntity<>(service.savePublishingHouse(mapper.requestPublishingHouseDtoToPublishingHouse(publishingHouse)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorPublishingHouseDTO("The PublishingHouse entered has not been created and saved: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update the publishing house entered if found by id to update save in database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "The PublishingHouse entered has been updated and saved.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RequestPublishingHouseDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The PublishingHouse entered has not been updated and saved: (case)",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PublishingHouseDTO> updatePublishingHouse(@RequestBody RequestPublishingHouseDTO publishingHouse, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.updatePublishingHouse(mapper.requestPublishingHouseDtoToPublishingHouse(publishingHouse), id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorPublishingHouseDTO("The PublishingHouse entered has not been updated and saved: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete by means of the entered id an publishing house stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "The PublishingHouse entered has been removed.",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "The PublishingHouse entered has not been removed.",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<PublishingHouseDTO> deletePublishingHouse(@PathVariable Long id) {
        try {
            service.deletePublishingHouse(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorPublishingHouseDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
