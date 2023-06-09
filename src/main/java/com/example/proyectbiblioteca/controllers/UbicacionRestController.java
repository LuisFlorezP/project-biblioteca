package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.ubicacion.ErrorUbicacionDTO;
import com.example.proyectbiblioteca.dto.ubicacion.ResponseUbicacionDTO;
import com.example.proyectbiblioteca.dto.ubicacion.UbicacionDTO;
import com.example.proyectbiblioteca.entities.Ubicacion;
import com.example.proyectbiblioteca.services.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class UbicacionRestController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping("/")
    public ResponseEntity<List<UbicacionDTO>> getAllLocations() {
        try {
            return new ResponseEntity<>(new ArrayList<>(ubicacionService.getAllLocations()), HttpStatus.OK);
        } catch (Exception e) {
            List<UbicacionDTO> ubicacionDTOS = new ArrayList<>();
            ubicacionDTOS.add(new ErrorUbicacionDTO(e.getMessage()));
            return new ResponseEntity<>(ubicacionDTOS, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionDTO> getLocation(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(ubicacionService.getLocation(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorUbicacionDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<UbicacionDTO> saveLocation(@RequestBody Ubicacion ubicacion) {
        try {
            return new ResponseEntity<>(ubicacionService.saveLocation(ubicacion), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorUbicacionDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDTO> updateLocation(@RequestBody Ubicacion ubicacion, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(ubicacionService.updateLocation(ubicacion, id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorUbicacionDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UbicacionDTO> deleteLocation(@PathVariable Long id) {
        try {
            ubicacionService.deleteLocation(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorUbicacionDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
