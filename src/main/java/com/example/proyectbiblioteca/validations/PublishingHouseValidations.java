package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.PublishingHouse;
import java.util.Optional;

public class PublishingHouseValidations {

    public static boolean publishingHousePresent(Optional<PublishingHouse> editorial) {
        return editorial.isPresent();
    }

    public static boolean verifyNombre(String nombre) {
        return nombre == null || nombre.length() < 2 || nombre.length() > 30;
    }

    public static boolean verifyDescripcionEditorial(String descripcion) {
        return descripcion == null || descripcion.length() > 300;
    }

    public static boolean nombrePresentEqualDifferent(String editorialNombre, String editorialId) {
        return !editorialId.equals(editorialNombre);
    }
}
