package com.example.proyectbiblioteca.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "categoria_entity")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    @Column(name = "descripcion", length = 255)
    private String descripcion;
}
