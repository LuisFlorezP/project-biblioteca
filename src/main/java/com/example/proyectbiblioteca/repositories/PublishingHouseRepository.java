package com.example.proyectbiblioteca.repositories;

import com.example.proyectbiblioteca.entities.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {

    Optional<PublishingHouse> findByNombre(String nombre);
}
