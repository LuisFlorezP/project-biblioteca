package com.example.proyectbiblioteca.repositories;

import com.example.proyectbiblioteca.entities.Autor;
import com.example.proyectbiblioteca.entities.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
}
