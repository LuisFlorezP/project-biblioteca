package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Author;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class AuthorValidationsTest {

    @Test
    public void authorPresent_present() {
        assertTrue(AuthorValidations.authorPresent(Optional.of(new Author())));
    }

    @Test
    public void authorPresent_empty() {
        assertFalse(AuthorValidations.authorPresent(Optional.empty()));
    }

    @Test
    public void verifyPseudonimo_correct() {
        assertFalse(AuthorValidations.verifyPseudonimo("aaaaaaaa"));
    }

    @Test
    public void verifyPseudonimo_null() {
        assertTrue(AuthorValidations.verifyPseudonimo(null));
    }

    @Test
    public void verifyPseudonimo_empty() {
        assertTrue(AuthorValidations.verifyPseudonimo(""));
    }

    @Test
    public void pseudonimoPresentEqualDifferent_equal() {
        Author authorPost = new Author();
        authorPost.setPseudonimo("test");
        Author authorGet = new Author();
        authorGet.setPseudonimo("test");
        assertFalse(AuthorValidations.pseudonimoPresentEqualDifferent(authorPost, authorGet));
    }

    @Test
    public void pseudonimoPresentEqualDifferent_different() {
        Author authorPost = new Author();
        authorPost.setPseudonimo("test");
        Author authorGet = new Author();
        authorGet.setPseudonimo("no_test");
        assertTrue(AuthorValidations.pseudonimoPresentEqualDifferent(authorPost, authorGet));
    }

    @Test
    public void verifyEmail_correct() {
        assertFalse( AuthorValidations.verifyEmail("aaa@gmail.com"));
    }

    @Test
    public void verifyEmail_invalid_null() {
        assertTrue( AuthorValidations.verifyEmail(null));
    }

    @Test
    public void verifyEmail_invalid_empty() {
        assertTrue( AuthorValidations.verifyEmail(""));
    }

    @Test
    public void verifyEmail_invalid_sin_nombre() {
        assertTrue( AuthorValidations.verifyEmail("@gmail.com"));
    }

    @Test
    public void verifyEmail_invalid_sin_arroba() {
        assertTrue( AuthorValidations.verifyEmail("aaagmail.com"));
    }

    @Test
    public void verifyEmail_invalid_sin_dominio() {
        assertTrue( AuthorValidations.verifyEmail("aaa@"));
    }

    @Test
    public void verifyEmail_invalid_sin_organizacion() {
        assertTrue( AuthorValidations.verifyEmail("aaa@.com"));
    }

    @Test
    public void verifyEmail_invalid_sin_tipo() {
        assertTrue( AuthorValidations.verifyEmail("aaa@gmail"));
    }

    @Test
    public void verifyNombreApellidoPseudonimo_NOT_nombre_NOT_apellido_NOT_pseudonimo() {
        assertTrue(AuthorValidations.verifyNombreApellidoPseudonimo(null, null, null));
    }

    @Test
    public void verifyNombreApellidoPseudonimo_YES_nombre_YES_apellido_NOT_pseudonimo() {
        assertFalse(AuthorValidations.verifyNombreApellidoPseudonimo("test", "test", null));
    }

    @Test
    public void verifyNombreApellidoPseudonimo_NOT_nombre_YES_apellido_NOT_pseudonimo() {
        assertTrue(AuthorValidations.verifyNombreApellidoPseudonimo(null, "test", null));
    }

    @Test
    public void verifyNombreApellidoPseudonimo_YES_nombre_NOT_apellido_NOT_pseudonimo() {
        assertTrue(AuthorValidations.verifyNombreApellidoPseudonimo("test", null, null));
    }

    @Test
    public void verifyNombreApellidoPseudonimo_YES_nombre_YES_apellido_YES_pseudonimo() {
        assertFalse(AuthorValidations.verifyNombreApellidoPseudonimo("test", "test", "test"));
    }

    @Test
    public void verifyNombreApellidoPseudonimo_NOT_nombre_NOT_apellido_YES_pseudonimo() {
        assertFalse(AuthorValidations.verifyNombreApellidoPseudonimo(null, null, "test"));
    }

    @Test
    public void verifyNombreApellidoPseudonimo_NOT_nombre_YES_apellido_YES_pseudonimo() {
        assertFalse(AuthorValidations.verifyNombreApellidoPseudonimo(null, "test", "test"));
    }

    @Test
    public void verifyNombreApellidoPseudonimo_YES_nombre_NOT_apellido_YES_pseudonimo() {
        assertFalse(AuthorValidations.verifyNombreApellidoPseudonimo("test", null, "test"));
    }

    @Test
    public void verifyNacionalidad_correct() {
        assertFalse(AuthorValidations.verifyNacionalidad(1L));
    }

    @Test
    public void verifyNacionalidad_pais_null() {
        assertTrue(AuthorValidations.verifyNacionalidad(null));
    }
}
