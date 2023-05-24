package com.example.proyectbiblioteca.repositories;

import com.example.proyectbiblioteca.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {


    Optional<Autor> findByPseudonimo(String pseudonimo);
}
