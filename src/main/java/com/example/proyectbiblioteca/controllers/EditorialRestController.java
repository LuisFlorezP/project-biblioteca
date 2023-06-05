package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.PublishingHouse;
import com.example.proyectbiblioteca.entities.Editorial;
import com.example.proyectbiblioteca.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<PublishingHouse>> getAllEditorials() {
        return new ResponseEntity<>(editorialService.getAllEditorial(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublishingHouse> getEditorial(@PathVariable Long id) {
        PublishingHouse publishingHouse = editorialService.getEditorial(id);
        if (publishingHouse != null) {
            return new ResponseEntity<>(publishingHouse, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<PublishingHouse> saveEditorial(@RequestBody Editorial editorial) {
        PublishingHouse data = editorialService.saveEditorial(editorial);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublishingHouse> updateEditorial(@RequestBody Editorial editorial, @PathVariable Long id) {
        PublishingHouse data = editorialService.updateEditorial(editorial, id);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
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
