package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.AutorDTO;
import com.example.proyectbiblioteca.entities.Autor;
import com.example.proyectbiblioteca.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autors")
public class AutorRestController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/")
    public ResponseEntity<List<AutorDTO>> getAllAutors() {
        return ResponseEntity.ok(autorService.getAllAutor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> getAutor(@PathVariable Long id) {
        AutorDTO autorDTO = autorService.getAutor(id);
        if (autorDTO != null) {
            return new ResponseEntity<>(autorDTO, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<AutorDTO> saveAutor(@RequestBody Autor autor) {
        AutorDTO data = autorService.saveAutor(autor);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> updateAutor(@RequestBody Autor autor, @PathVariable Long id) {
        AutorDTO data = autorService.updateAutor(autor, id);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AutorDTO> deleteAutor(@PathVariable Long id) {
        if (autorService.deleteAutor(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
