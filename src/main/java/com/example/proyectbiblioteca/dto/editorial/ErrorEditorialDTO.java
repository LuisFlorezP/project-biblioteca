package com.example.proyectbiblioteca.dto.editorial;

public class ErrorEditorialDTO extends EditorialDTO {

    private String error;

    public ErrorEditorialDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
