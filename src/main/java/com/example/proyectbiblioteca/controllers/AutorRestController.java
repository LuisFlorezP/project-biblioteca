package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.Author;
import com.example.proyectbiblioteca.entities.Autor;
import com.example.proyectbiblioteca.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autors")
public class AutorRestController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/")
    public ResponseEntity<List<Author>> getAllAutors() {
        return ResponseEntity.ok(autorService.getAllAutor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAutor(@PathVariable Long id) {
        Author author = autorService.getAutor(id);
        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Author> saveAutor(@RequestBody Autor autor) {
        Author data = autorService.saveAutor(autor);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAutor(@RequestBody Autor autor, @PathVariable Long id) {
        Author data = autorService.updateAutor(autor, id);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAutor(@PathVariable Long id) {
        if (autorService.deleteAutor(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
