package com.example.proyectbiblioteca.repositories;

import com.example.proyectbiblioteca.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByPseudonimo(String pseudonimo);
}
