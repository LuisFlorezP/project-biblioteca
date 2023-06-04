package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Autor;
import com.example.proyectbiblioteca.entities.Pais;

import java.util.Optional;
import java.util.regex.Pattern;

public class GenerateValidation {

    public static boolean verificarEmail(String email) {
        if (email == null) {
            return true;
        }
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (pattern.matcher(email).find()) {
            return false;
        }
        return true;
    }

    public static boolean verificarNombreApellidoPseudonimo(Autor autor) {
        if (autor.getPseudonimo() == null) {
            if (autor.getNombre() == null || autor.getApellido() == null) {
                return true;
            }
        } else if (autor.getNombre() == null && autor.getApellido() == null) {
            return false;
        } else if (autor.getNombre() == null || autor.getApellido() == null) {
            return true;
        }
        return false;
    }

    public static boolean verificarNacionalidad(Pais pais) {
        if (pais == null) {
            return true;
        }
        return false;
    }

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
