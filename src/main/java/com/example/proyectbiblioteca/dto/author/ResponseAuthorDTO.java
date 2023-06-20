package com.example.proyectbiblioteca.dto.author;

import com.example.proyectbiblioteca.entities.Pais;

public class ResponseAuthorDTO extends AuthorDTO {

    private String name;
    private String lastName;
    private String pseudonym;
    private String email;
    private Pais nationality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pais getNationality() {
        return nationality;
    }

    public void setNationality(Pais nationality) {
        this.nationality = nationality;
    }
}
