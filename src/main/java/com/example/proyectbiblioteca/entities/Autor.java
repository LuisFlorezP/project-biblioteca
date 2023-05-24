package com.example.proyectbiblioteca.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "autorEntity")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "pseudonimo", unique = true)
    private String pseudonimo;
    @ManyToOne(optional = false)
    @JoinColumn(name = "pais_id")
    private Pais nacionalidad;
    @Column(name = "email")
    private String email;
}
