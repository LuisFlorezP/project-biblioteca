package com.example.proyectbiblioteca.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pais_entity")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre", unique = true)
    private String nombre;
    @OneToMany(mappedBy = "nacionalidad", cascade = CascadeType.ALL)
    private Set<Autor> autors = new HashSet<>();
}
