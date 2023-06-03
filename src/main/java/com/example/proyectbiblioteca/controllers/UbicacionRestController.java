package com.example.proyectbiblioteca.controllers;

import com.example.proyectbiblioteca.dto.Location;
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
    public ResponseEntity<List<Location>> getAllLocations() {
        return new ResponseEntity<>(ubicacionService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Long id) {
        Location location = ubicacionService.getLocation(id);
        if (location != null) {
            return new ResponseEntity<>(location, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Location> saveLocation(@RequestBody Location location) {
        Location data = ubicacionService.saveLocation(location);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@RequestBody Location location, @PathVariable Long id) {
        Location data = ubicacionService.updateLocation(location, id);
        if (data != null) {
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable Long id) {
        if (ubicacionService.deleteLocation(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
