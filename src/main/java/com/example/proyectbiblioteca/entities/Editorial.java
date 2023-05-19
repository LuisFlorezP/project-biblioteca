package com.example.proyectbiblioteca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Validated
@Table(name = "editorial")
public class Editorial {

    @Id
    @Column(name = "nombre", nullable = false, length = 30)
    @Size(min = 2, max = 30)
    private String nombre;
    @Column(name = "descripcion", nullable = false, length = 300)
    @Size(max = 300)
    private String descripcion;
}
