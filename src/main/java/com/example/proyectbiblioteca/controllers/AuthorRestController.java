package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.author.*;
import com.example.proyectbiblioteca.dto.author.AuthorDTO;
import com.example.proyectbiblioteca.dto.author.ResponseAuthorDTO;
import com.example.proyectbiblioteca.mappers.AuthorMapper;
import com.example.proyectbiblioteca.services.AuthorService;
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
@RequestMapping("/authors")
@Tag(name = "Services of Authors", description = "Methods to be able to create, read, update and delete (CRUD) services corresponding to the Author entity.")
public class AuthorRestController {

    @Autowired
    private AuthorService service;
    @Autowired
    private AuthorMapper mapper;

    @Operation(summary = "Read all authors stored in database.")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Records for the Author entity have been found.",
                content = {
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseAuthorDTO.class))
                }),
        @ApiResponse(
                responseCode = "400",
                description = "The Author entity records could not be read.",
                content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        try {
            return ResponseEntity.ok(new ArrayList<>(service.getAllAuthors()));
        } catch (Exception e) {
            List<AuthorDTO> authorDTOS = new ArrayList<>();
            authorDTOS.add(new ErrorAuthorDTO(e.getMessage()));
            return new ResponseEntity<>(authorDTOS, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Read through the entered id an author stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The Author entity record has been found.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseAuthorDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "The Author entity record was not found.",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getAuthor(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorAuthorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create the author entered to save it in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "The Author entered has been created and saved.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RequestAuthorDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The Author entered has not been created and saved: (case)",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<AuthorDTO> saveAuthor(@RequestBody RequestAuthorDTO author) {
        try {
            return new ResponseEntity<>(service.saveAuthor(mapper.requestAuthorDtoToAuthor(author)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorAuthorDTO("The Author entered has not been created and saved: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update the author entered if found by id to update save in database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "The Author entered has been updated and saved.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RequestAuthorDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The Author entered has not been updated and saved: (case)",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody RequestAuthorDTO author, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.updateAuthor(mapper.requestAuthorDtoToAuthor(author), id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorAuthorDTO("The Author entered has not been updated and saved: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete by means of the entered id an author stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "The Author entered has been removed.",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "The Author entered has not been removed.",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDTO> deleteAuthor(@PathVariable Long id) {
        try {
            service.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorAuthorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
