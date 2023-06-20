package com.example.proyectbiblioteca.dto.location;

public class ResponseLocationDTO extends LocationDTO {

    private String floor;
    private String lounge;
    private String shelf;

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getLounge() {
        return lounge;
    }

    public void setLounge(String lounge) {
        this.lounge = lounge;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }
}
