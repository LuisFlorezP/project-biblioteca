package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.categoria.CategoriaDTO;
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
    public ResponseEntity<List<CategoriaDTO>> getAllCategories() {
        return new ResponseEntity<>(categoriaService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategory(@PathVariable Long id) {
        CategoriaDTO categoriaDTO = categoriaService.getCategory(id);
        if (categoriaDTO != null) {
            return new  ResponseEntity<>(categoriaDTO, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<CategoriaDTO> saveCategory(@RequestBody Categoria categoria) {
        CategoriaDTO data = categoriaService.saveCategory(categoria);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategory(@RequestBody Categoria categoria, @PathVariable Long id) {
        CategoriaDTO data = categoriaService.updateCategory(categoria, id);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDTO> deleteCategory(@PathVariable Long id) {
        if (categoriaService.deleteCategory(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
