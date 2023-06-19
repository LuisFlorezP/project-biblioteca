package com.example.proyectbiblioteca.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pais_entity")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre", unique = true)
    private String nombre;
    @OneToMany(mappedBy = "nacionalidad")
    @JsonManagedReference
    @JsonIgnore
    private List<Author> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
