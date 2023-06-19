package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Author;
import java.util.Optional;
import java.util.regex.Pattern;

public class AuthorValidations {

    public static boolean authorPresent(Optional<Author> author) {
        return author.isPresent();
    }

    public static boolean verifyPseudonimo(String pseudonimo) {
        return (pseudonimo == null || pseudonimo.length() == 0);
    }

    public static boolean pseudonimoPresentEqualDifferent(Author authorPseudonimo, Author authorId) {
        return !authorId.getPseudonimo().equals(authorPseudonimo.getPseudonimo());
    }

    public static boolean verifyEmail(String email) {
        if (email == null || email.isEmpty()) {
            return true;
        }
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        return !pattern.matcher(email).find();
    }

    public static boolean verifyNombreApellidoPseudonimo(String nombre, String apellido, String pseudonimo) {
        return (pseudonimo == null && (nombre == null || apellido == null));
    }

    public static boolean verifyNacionalidad(Long idPais) {
        return idPais == null;
    }
}
