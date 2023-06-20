package com.example.proyectbiblioteca.dto.category;

public class ErrorCategoryDTO extends CategoryDTO {

    private String error;

    public ErrorCategoryDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
