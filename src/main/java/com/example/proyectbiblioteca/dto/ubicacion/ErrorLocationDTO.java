package com.example.proyectbiblioteca.dto.ubicacion;

public class ErrorLocationDTO extends LocationDTO {

    private String error;

    public ErrorLocationDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
