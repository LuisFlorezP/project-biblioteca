package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.entities.Editorial;
import com.example.proyectbiblioteca.services.EditorialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/editorials")
public class EditorialRestController {

    private EditorialService editorialService;

    @GetMapping("")
    public ResponseEntity<List<Editorial>> getAllEditorials() {
        return ResponseEntity.ok(editorialService.getAllEditorial());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Editorial>> getEditorial(@RequestBody String nombre) {
        Optional<Editorial> editorial = editorialService.getEditorial(nombre);
        if (editorial.equals(Optional.empty())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editorial);
    }
}
