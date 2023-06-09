package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.ubicacion.ResponseUbicacionDTO;
import com.example.proyectbiblioteca.entities.Ubicacion;
import com.example.proyectbiblioteca.services.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class UbicacionRestController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping("/")
    public ResponseEntity<List<ResponseUbicacionDTO>> getAllLocations() {
        return new ResponseEntity<>(ubicacionService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUbicacionDTO> getLocation(@PathVariable Long id) {
        ResponseUbicacionDTO responseUbicacionDTO = ubicacionService.getLocation(id);
        if (responseUbicacionDTO != null) {
            return new ResponseEntity<>(responseUbicacionDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseUbicacionDTO> saveLocation(@RequestBody Ubicacion ubicacion) {
        ResponseUbicacionDTO data = ubicacionService.saveLocation(ubicacion);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUbicacionDTO> updateLocation(@RequestBody Ubicacion ubicacion, @PathVariable Long id) {
        ResponseUbicacionDTO data = ubicacionService.updateLocation(ubicacion, id);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseUbicacionDTO> deleteLocation(@PathVariable Long id) {
        if (ubicacionService.deleteLocation(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
