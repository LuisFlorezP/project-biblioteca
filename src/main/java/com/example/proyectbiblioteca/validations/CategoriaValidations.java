package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Categoria;
import java.util.Optional;

public class CategoriaValidations {

    public static boolean categoriaPresente(Optional<Categoria> categoria) {
        return categoria.isPresent();
    }

    public static boolean verificarDescripcionCategoria(String descripcion) {
        return descripcion.length() > 255;
    }

    public static boolean nombrePresenteIgualDiferente(Optional<Categoria> categoriaNombre, Categoria categoriaId) {
        return categoriaPresente(categoriaNombre) && !categoriaId.getNombre().equals(categoriaNombre.get().getNombre());
    }
}
