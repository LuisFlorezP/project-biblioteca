package com.example.proyectbiblioteca.dto.categoria;

public class ErrorCategoriaDTO extends CategoriaDTO {

    private String error;

    public ErrorCategoriaDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
