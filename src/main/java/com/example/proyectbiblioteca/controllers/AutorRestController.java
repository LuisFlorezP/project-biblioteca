package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.autor.ResponseAutorDTO;
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
    public ResponseEntity<List<ResponseAutorDTO>> getAllAutors() {
        return ResponseEntity.ok(autorService.getAllAutor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAutorDTO> getAutor(@PathVariable Long id) {
        ResponseAutorDTO responseAutorDTO = autorService.getAutor(id);
        if (responseAutorDTO != null) {
            return new ResponseEntity<>(responseAutorDTO, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<ResponseAutorDTO> saveAutor(@RequestBody Autor autor) {
        ResponseAutorDTO data = autorService.saveAutor(autor);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAutorDTO> updateAutor(@RequestBody Autor autor, @PathVariable Long id) {
        ResponseAutorDTO data = autorService.updateAutor(autor, id);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseAutorDTO> deleteAutor(@PathVariable Long id) {
        if (autorService.deleteAutor(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
