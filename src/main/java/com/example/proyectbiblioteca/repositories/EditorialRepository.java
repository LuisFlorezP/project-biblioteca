package com.example.proyectbiblioteca.repositories;

import com.example.proyectbiblioteca.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EditorialRepository extends JpaRepository<Editorial, String> {
}
