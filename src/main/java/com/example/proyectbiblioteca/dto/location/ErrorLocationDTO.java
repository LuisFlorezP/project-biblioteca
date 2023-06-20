package com.example.proyectbiblioteca.dto.location;

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
