package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.entities.Autor;
import com.example.proyectbiblioteca.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Autor>> getAllAutors() {
        return ResponseEntity.ok(autorService.getAllAutor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Autor>> getAutor(@PathVariable Long id) {
        Optional<Autor> autor = autorService.getAutor(id);
        if (autor.equals(Optional.empty())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    @PostMapping("/")
    public ResponseEntity<Optional<Autor>> saveAutor(@RequestBody Autor autor) {
        Optional<Autor> data = autorService.saveAutor(autor);
        if (data.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAutor(@RequestBody Autor autor, @PathVariable Long id) {
        Autor data = autorService.updateAutor(autor, id);
        if (data != null) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Autor> deleteAutor(@PathVariable Long id) {
        if (autorService.deleteAutor(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
