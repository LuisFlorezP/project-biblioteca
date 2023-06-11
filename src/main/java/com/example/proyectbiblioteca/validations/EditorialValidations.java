package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Editorial;
import java.util.Optional;

public class EditorialValidations {

    public static boolean editorialPresente(Optional<Editorial> editorial) {
        return editorial.isPresent();
    }

    public static boolean verificarNombre(String nombre) {
        return nombre.length() < 2 || nombre.length() > 30;
    }

    public static boolean verificarDescripcionEditorial(String descripcion) {
        return descripcion.length() > 300;
    }

    public static boolean nombrePresenteIgualDiferente(Optional<Editorial> editorialNombre, Editorial editorialId) {
        return (editorialNombre.isPresent() && !editorialId.getNombre().equals(editorialNombre.get().getNombre()));
    }
}
