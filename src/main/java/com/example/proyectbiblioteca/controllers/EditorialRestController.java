package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.entities.Editorial;
import com.example.proyectbiblioteca.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/editorials")
public class EditorialRestController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/")
    public ResponseEntity<List<Editorial>> getAllEditorials() {
        return ResponseEntity.ok(editorialService.getAllEditorial());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Editorial>> getEditorial(@PathVariable Long id) {
        Optional<Editorial> editorial = editorialService.getEditorial(id);
        if (editorial.equals(Optional.empty())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editorial);
    }

    @PostMapping("/")
    public ResponseEntity<Editorial> saveEditorial(@RequestBody Editorial editorial) {
        return ResponseEntity.ok(editorialService.saveEditorial(editorial));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editorial> updateEditorial(@RequestBody Editorial editorial, @PathVariable Long id) {
        Editorial data = editorialService.updateEditorial(editorial, id);
        if (data != null) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Editorial> deleteEditorial(@PathVariable Long id) {
        if (editorialService.deleteEditorial(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
