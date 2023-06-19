package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Category;
import java.util.Optional;

public class CategoryValidations {

    public static boolean categoryPresent(Optional<Category> categoria) {
        return categoria.isPresent();
    }

    public static boolean verifyNombre(String nombre) {
        return (nombre == null || nombre.length() == 0);
    }

    public static boolean verifyDescripcionCategory(String descripcion) {
        return descripcion == null || descripcion.length() > 255;
    }

    public static boolean nombrePresentEqualDifferent(String categoriaNombre, String categoriaId) {
        return !categoriaId.equals(categoriaNombre);
    }
}
