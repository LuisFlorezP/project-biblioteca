package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Autor;

import java.util.regex.Pattern;

public class GenerateValidation {

    public static boolean verificarEmail(String email) {
        if (email == null) {
            return true;
        }
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if (pattern.matcher(email).find()) {
            return true;
        }
        return false;
    }

    public static boolean verificarNombreApellidoPseudonimo(Autor autor, boolean nacionalidad) {
        if (autor.getNacionalidad() == null && nacionalidad) {
            return true;
        }
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

    public static boolean verificarMismoPseudonimo(String pseudonimoDB, String pseudonimoNew) {
        if (pseudonimoDB.equals(pseudonimoNew)) {
            return false;
        }
        return true;
    }
}
