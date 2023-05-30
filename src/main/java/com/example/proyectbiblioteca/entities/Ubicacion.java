package com.example.proyectbiblioteca.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ubicacion_entity")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "piso", nullable = false)
    private String piso;
    @Column(name = "salon", nullable = false)
    private String salon;
    @Column(name = "estante", nullable = false)
    private String estante;
}
