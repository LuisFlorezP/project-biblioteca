package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Autor;
import com.example.proyectbiblioteca.entities.Pais;

import java.util.Optional;
import java.util.regex.Pattern;

public class AutorValidations {

    public static boolean autorPresente(Optional<Autor> autor) {
        return autor.isPresent();
    }

    public static boolean pseudonimoPresente(Optional<Autor> autorPseudonimo) {
        return autorPseudonimo.isPresent();
    }

    public static boolean pseudonimoPresenteIgualDIferente(Optional<Autor> autorPseudonimo, Autor autorId) {
        return pseudonimoPresente(autorPseudonimo) && !autorId.getPseudonimo().equals(autorPseudonimo.get().getPseudonimo());
    }

    public static boolean verificarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return true;
        }
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        return !pattern.matcher(email).find();
    }

    public static boolean verificarNombreApellidoPseudonimo(String pseudonimo, String nombre, String apellido) {
        if (pseudonimo == null || pseudonimo.isEmpty()) return true;
        else return (nombre == null && apellido == null);
    }

    public static boolean verificarNacionalidad(Pais pais) {
        if (pais == null) {
            return true;
        } else return pais.getId() == null;
    }
}
