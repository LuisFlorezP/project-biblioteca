package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.ubicacion.UbicacionDTO;
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
    public ResponseEntity<List<UbicacionDTO>> getAllLocations() {
        return new ResponseEntity<>(ubicacionService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionDTO> getLocation(@PathVariable Long id) {
        UbicacionDTO ubicacionDTO = ubicacionService.getLocation(id);
        if (ubicacionDTO != null) {
            return new ResponseEntity<>(ubicacionDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<UbicacionDTO> saveLocation(@RequestBody Ubicacion ubicacion) {
        UbicacionDTO data = ubicacionService.saveLocation(ubicacion);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDTO> updateLocation(@RequestBody Ubicacion ubicacion, @PathVariable Long id) {
        UbicacionDTO data = ubicacionService.updateLocation(ubicacion, id);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UbicacionDTO> deleteLocation(@PathVariable Long id) {
        if (ubicacionService.deleteLocation(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
