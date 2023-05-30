package com.example.proyectbiblioteca.controllers;

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
    public ResponseEntity<List<Ubicacion>> getAllLocations() {
        return new ResponseEntity<>(ubicacionService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getLocation(@PathVariable Long id) {
        return ubicacionService.getLocation(id)
                .map(ubicacion -> new ResponseEntity<>(ubicacion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Ubicacion> saveLocation(@RequestBody Ubicacion ubicacion) {
        return ubicacionService.saveLocation(ubicacion)
                .map(data -> new ResponseEntity<>(data, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> updateLocation(@RequestBody Ubicacion ubicacion, @PathVariable Long id) {
        Ubicacion data = ubicacionService.updateLocation(ubicacion, id);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLocation(@PathVariable Long id) {
        if (ubicacionService.deleteLocation(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
