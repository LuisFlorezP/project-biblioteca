package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Autor;
import com.example.proyectbiblioteca.entities.Pais;
import java.util.regex.Pattern;

public class GenerateValidation {

    public static boolean verificarDescripcionCategoria(int longitud) {
        if (longitud > 255) {
            return true;
        }
        return false;
    }

    public static boolean verificarNombre(String nombre) {
        if (nombre.length() < 2 || nombre.length() > 30) {
            return true;
        }
        return false;
    }

    public static boolean verificarDescripcionEditorial(String descripcion) {
        if (descripcion.length() > 300) {
            return true;
        }
        return false;
    }
}
