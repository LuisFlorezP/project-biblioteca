package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.editorial.EditorialDTO;
import com.example.proyectbiblioteca.dto.editorial.ErrorEditorialDTO;
import com.example.proyectbiblioteca.dto.editorial.ResponseEditorialDTO;
import com.example.proyectbiblioteca.entities.Editorial;
import com.example.proyectbiblioteca.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/editorials")
public class EditorialRestController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/")
    public ResponseEntity<List<EditorialDTO>> getAllEditorials() {
        try {
            return ResponseEntity.ok(new ArrayList<>(editorialService.getAllEditorials()));
        } catch (Exception e) {
            List<EditorialDTO> editorialDTOS = new ArrayList<>();
            editorialDTOS.add(new ErrorEditorialDTO(e.getMessage()));
            return new ResponseEntity<>(editorialDTOS, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditorialDTO> getEditorial(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(editorialService.getEditorial(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorEditorialDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<EditorialDTO> saveEditorial(@RequestBody Editorial editorial) {
        try {
            return new ResponseEntity<>(editorialService.saveEditorial(editorial), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorEditorialDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditorialDTO> updateEditorial(@RequestBody Editorial editorial, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(editorialService.updateEditorial(editorial, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorEditorialDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EditorialDTO> deleteEditorial(@PathVariable Long id) {
        try {
            editorialService.deleteEditorial(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorEditorialDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
