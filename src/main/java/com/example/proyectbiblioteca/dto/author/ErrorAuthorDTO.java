package com.example.proyectbiblioteca.dto.author;

public class ErrorAuthorDTO extends AuthorDTO {

    private String error;

    public ErrorAuthorDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
