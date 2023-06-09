package com.example.proyectbiblioteca.dto.ubicacion;

public class ErrorUbicacionDTO extends UbicacionDTO {

    private String error;

    public ErrorUbicacionDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
