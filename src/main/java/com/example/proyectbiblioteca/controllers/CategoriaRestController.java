package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.categoria.CategoriaDTO;
import com.example.proyectbiblioteca.dto.categoria.ErrorCategoriaDTO;
import com.example.proyectbiblioteca.dto.categoria.ResponseCategoriaDTO;
import com.example.proyectbiblioteca.entities.Categoria;
import com.example.proyectbiblioteca.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriaRestController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public ResponseEntity<List<CategoriaDTO>> getAllCategories() {
        try {
            return ResponseEntity.ok(new ArrayList<>(categoriaService.getAllCategories()));
        } catch (Exception e) {
            List<CategoriaDTO> categoriaDTOS = new ArrayList<>();
            categoriaDTOS.add(new ErrorCategoriaDTO(e.getMessage()));
            return new ResponseEntity<>(categoriaDTOS, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategory(@PathVariable Long id) {
        try {
            return new  ResponseEntity<>(categoriaService.getCategory(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorCategoriaDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<CategoriaDTO> saveCategory(@RequestBody Categoria categoria) {
        try {
            return new ResponseEntity<>(categoriaService.saveCategory(categoria), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorCategoriaDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategory(@RequestBody Categoria categoria, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(categoriaService.updateCategory(categoria, id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorCategoriaDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDTO> deleteCategory(@PathVariable Long id) {
        try {
            categoriaService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorCategoriaDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
