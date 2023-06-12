package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Autor;
import com.example.proyectbiblioteca.entities.Pais;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;

public class AutorValidationsTest {

    @Test
    public void autorPresente_present() {
        assertTrue(AutorValidations.autorPresente(Optional.of(new Autor())));
    }

    @Test
    public void autorPresente_empty() {
        assertFalse(AutorValidations.autorPresente(Optional.empty()));
    }

    @Test
    public void verificarPseudonimo_valido() {
        assertFalse(AutorValidations.verificarPseudonimo("aaaaaaaa"));
    }

    @Test
    public void verificarPseudonimo_nulo() {
        assertTrue(AutorValidations.verificarPseudonimo(null));
    }

    @Test
    public void verificarPseudonimo_vacio() {
        assertTrue(AutorValidations.verificarPseudonimo(""));
    }

    @Test
    public void pseudonimoPresenteIgualDiferente_igual() {
        Autor autorPost = new Autor();
        autorPost.setPseudonimo("test");
        Autor autorGet = new Autor();
        autorGet.setPseudonimo("test");
        assertFalse(AutorValidations.pseudonimoPresenteIgualDiferente(autorPost, autorGet));
    }

    @Test
    public void pseudonimoPresenteIgualDiferente_diferente() {
        Autor autorPost = new Autor();
        autorPost.setPseudonimo("test");
        Autor autorGet = new Autor();
        autorGet.setPseudonimo("no_test");
        assertTrue(AutorValidations.pseudonimoPresenteIgualDiferente(autorPost, autorGet));
    }

    @Test
    public void verificarEmail_valido() {
        assertFalse( AutorValidations.verificarEmail("aaa@gmail.com"));
    }

    @Test
    public void verificarEmail_invalido_nulo() {
        assertTrue( AutorValidations.verificarEmail(null));
    }

    @Test
    public void verificarEmail_invalido_vacio() {
        assertTrue( AutorValidations.verificarEmail(""));
    }

    @Test
    public void verificarEmail_invalido_sin_nombre() {
        assertTrue( AutorValidations.verificarEmail("@gmail.com"));
    }

    @Test
    public void verificarEmail_invalido_sin_arroba() {
        assertTrue( AutorValidations.verificarEmail("aaagmail.com"));
    }

    @Test
    public void verificarEmail_invalido_sin_dominio() {
        assertTrue( AutorValidations.verificarEmail("aaa@"));
    }

    @Test
    public void verificarEmail_invalido_sin_organizacion() {
        assertTrue( AutorValidations.verificarEmail("aaa@.com"));
    }

    @Test
    public void verificarEmail_invalido_sin_tipo() {
        assertTrue( AutorValidations.verificarEmail("aaa@gmail"));
    }

    @Test
    public void verificarNombreApellidoPseudonimo_NO_nombre_NO_apellido_NO_pseudonimo() {
        assertTrue(AutorValidations.verificarNombreApellidoPseudonimo(null, null, null));
    }

    @Test
    public void verificarNombreApellidoPseudonimo_SI_nombre_SI_apellido_NO_pseudonimo() {
        assertFalse(AutorValidations.verificarNombreApellidoPseudonimo("test", "test", null));
    }

    @Test
    public void verificarNombreApellidoPseudonimo_NO_nombre_SI_apellido_NO_pseudonimo() {
        assertTrue(AutorValidations.verificarNombreApellidoPseudonimo(null, "test", null));
    }

    @Test
    public void verificarNombreApellidoPseudonimo_SI_nombre_NO_apellido_NO_pseudonimo() {
        assertTrue(AutorValidations.verificarNombreApellidoPseudonimo("test", null, null));
    }

    @Test
    public void verificarNombreApellidoPseudonimo_SI_nombre_SI_apellido_SI_pseudonimo() {
        assertFalse(AutorValidations.verificarNombreApellidoPseudonimo("test", "test", "test"));
    }

    @Test
    public void verificarNombreApellidoPseudonimo_NO_nombre_NO_apellido_SI_pseudonimo() {
        assertFalse(AutorValidations.verificarNombreApellidoPseudonimo(null, null, "test"));
    }

    @Test
    public void verificarNombreApellidoPseudonimo_NO_nombre_SI_apellido_SI_pseudonimo() {
        assertFalse(AutorValidations.verificarNombreApellidoPseudonimo(null, "test", "test"));
    }

    @Test
    public void verificarNombreApellidoPseudonimo_SI_nombre_NO_apellido_SI_pseudonimo() {
        assertFalse(AutorValidations.verificarNombreApellidoPseudonimo("test", null, "test"));
    }

    @Test
    public void verificarNacionalidad_valida() {
        assertFalse(AutorValidations.verificarNacionalidad(1L));
    }

    @Test
    public void verificarNacionalidad_pais_nulo() {
        assertTrue(AutorValidations.verificarNacionalidad(null));
    }
}
