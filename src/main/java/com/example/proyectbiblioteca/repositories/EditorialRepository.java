package com.example.proyectbiblioteca.repositories;

import com.example.proyectbiblioteca.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EditorialRepository extends JpaRepository<Editorial, String> {
}
