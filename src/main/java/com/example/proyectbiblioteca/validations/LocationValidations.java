package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Location;

import java.util.Optional;

public class LocationValidations {

    public static boolean locationPresent(Optional<Location> ubicacion) {
        return ubicacion.isPresent();
    }

    public static boolean attributePresent(String atributo) {
        return atributo == null || atributo.isEmpty();
    }
}
