package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.editorial.ResponseEditorialDTO;
import com.example.proyectbiblioteca.entities.Editorial;
import com.example.proyectbiblioteca.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/editorials")
public class EditorialRestController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/")
    public ResponseEntity<List<ResponseEditorialDTO>> getAllEditorials() {
        return new ResponseEntity<>(editorialService.getAllEditorial(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEditorialDTO> getEditorial(@PathVariable Long id) {
        ResponseEditorialDTO responseEditorialDTO = editorialService.getEditorial(id);
        if (responseEditorialDTO != null) {
            return new ResponseEntity<>(responseEditorialDTO, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<ResponseEditorialDTO> saveEditorial(@RequestBody Editorial editorial) {
        ResponseEditorialDTO data = editorialService.saveEditorial(editorial);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEditorialDTO> updateEditorial(@RequestBody Editorial editorial, @PathVariable Long id) {
        ResponseEditorialDTO data = editorialService.updateEditorial(editorial, id);
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
