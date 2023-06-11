package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Ubicacion;

import java.util.Optional;

public class UbicacionValidations {

    public static boolean ubicacionPresente(Optional<Ubicacion> ubicacion) {
        return ubicacion.isPresent();
    }

    public static boolean atributoPresente(String atributo) {
        return atributo == null || atributo.isEmpty();
    }
}
