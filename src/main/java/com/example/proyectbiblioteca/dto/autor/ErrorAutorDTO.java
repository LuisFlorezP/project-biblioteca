package com.example.proyectbiblioteca.dto.autor;

public class ErrorAutorDTO extends AutorDTO {

    private String error;

    public ErrorAutorDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
