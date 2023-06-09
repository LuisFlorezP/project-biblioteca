package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.categoria.ResponseCategoriaDTO;
import com.example.proyectbiblioteca.entities.Categoria;
import com.example.proyectbiblioteca.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriaRestController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public ResponseEntity<List<ResponseCategoriaDTO>> getAllCategories() {
        return new ResponseEntity<>(categoriaService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCategoriaDTO> getCategory(@PathVariable Long id) {
        ResponseCategoriaDTO responseCategoriaDTO = categoriaService.getCategory(id);
        if (responseCategoriaDTO != null) {
            return new  ResponseEntity<>(responseCategoriaDTO, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<ResponseCategoriaDTO> saveCategory(@RequestBody Categoria categoria) {
        ResponseCategoriaDTO data = categoriaService.saveCategory(categoria);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCategoriaDTO> updateCategory(@RequestBody Categoria categoria, @PathVariable Long id) {
        ResponseCategoriaDTO data = categoriaService.updateCategory(categoria, id);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseCategoriaDTO> deleteCategory(@PathVariable Long id) {
        if (categoriaService.deleteCategory(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
