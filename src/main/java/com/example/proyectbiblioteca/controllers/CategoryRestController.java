package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.category.CategoryDTO;
import com.example.proyectbiblioteca.dto.category.ErrorCategoryDTO;
import com.example.proyectbiblioteca.dto.category.RequestCategoryDTO;
import com.example.proyectbiblioteca.dto.category.ResponseCategoryDTO;
import com.example.proyectbiblioteca.mappers.CategoryMapper;
import com.example.proyectbiblioteca.services.CategoryService;
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
@RequestMapping("/categories")
@Tag(name = "Services of Categories", description = "Methods to be able to create, read, update and delete (CRUD) services corresponding to the Category entity.")
public class CategoryRestController {

    @Autowired
    private CategoryService service;
    @Autowired
    private CategoryMapper mapper;

    @Operation(summary = "Read all categories stored in database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Records for the Category entity have been found.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCategoryDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "The Category entity records could not be read.",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        try {
            return ResponseEntity.ok(new ArrayList<>(service.getAllCategories()));
        } catch (Exception e) {
            List<CategoryDTO> categoryDTOS = new ArrayList<>();
            categoryDTOS.add(new ErrorCategoryDTO(e.getMessage()));
            return new ResponseEntity<>(categoryDTOS, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Read through the entered id an category stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The Category entity record has been found.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseCategoryDTO.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "The Category entity record was not found.",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        try {
            return new  ResponseEntity<>(service.getCategory(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorCategoryDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create the category entered to save it in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "The Category entered has been created and saved.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RequestCategoryDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The Category entered has not been created and saved: (case)",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody RequestCategoryDTO category) {
        try {
            return new ResponseEntity<>(service.saveCategory(mapper.requestCategoryDtoToCategory(category)), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorCategoryDTO("The Category entered has not been created and saved: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update the category entered if found by id to update save in database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "The Category entered has been updated and saved.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RequestCategoryDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The Category entered has not been updated and saved: (case)",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody RequestCategoryDTO category, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.updateCategory(mapper.requestCategoryDtoToCategory(category), id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorCategoryDTO("The Category entered has not been updated and saved: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Delete by means of the entered id an category stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "The Category entered has been removed.",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "The Category entered has not been removed.",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long id) {
        try {
            service.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorCategoryDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
