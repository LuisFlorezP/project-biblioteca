package com.example.proyectbiblioteca.dto.publishinghouse;

public class ErrorPublishingHouseDTO extends PublishingHouseDTO {

    private String error;

    public ErrorPublishingHouseDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
