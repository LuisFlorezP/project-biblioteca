package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Editorial;
import org.junit.Test;
import java.util.Optional;
import static org.junit.Assert.*;

public class EditorialValidationsTest {

    @Test
    public void editorialPresente_present() {
        assertTrue(EditorialValidations.editorialPresente(Optional.of(new Editorial())));
    }

    @Test
    public void editorialPresente_empty() {
        assertFalse(EditorialValidations.editorialPresente(Optional.empty()));
    }

    @Test
    public void verificarNombre_invalido_menos_de_2_caracteres() {
        assertTrue(EditorialValidations.verificarNombre("a"));
    }

    @Test
    public void verificarNombre_valido_de_2_caracteres() {
        assertFalse(EditorialValidations.verificarNombre("aa"));
    }

    @Test
    public void verificarNombre_valido_entre_2_y_30_caracteres() {
        assertFalse(EditorialValidations.verificarNombre("aaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verificarNombre_valido_de_30_caracteres() {
        assertFalse(EditorialValidations.verificarNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verificarNombre_invalido_mas_de_30_caracteres() {
        assertTrue(EditorialValidations.verificarNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verificarNombre_nulo() {
        assertTrue(EditorialValidations.verificarNombre(null));
    }

    @Test
    public void verificarNombre_vacio() {
        assertTrue(EditorialValidations.verificarNombre(""));
    }

    @Test
    public void verificarDescripcionEditorial_valido_menos_de_300_caracteres() {
        assertFalse(EditorialValidations.verificarDescripcionEditorial("aaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verificarDescripcionEditorial_valido_de_300_caracteres() {
        assertFalse(EditorialValidations.verificarDescripcionEditorial("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verificarDescripcionEditorial_invalido_mas_de_300_caracteres() {
        assertTrue(EditorialValidations.verificarDescripcionEditorial("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verificarDescripcionEditorial_nulo() {
        assertTrue(EditorialValidations.verificarDescripcionEditorial(null));
    }

    @Test
    public void verificarDescripcionEditorial_vacio() {
        assertFalse(EditorialValidations.verificarDescripcionEditorial(""));
    }

    @Test
    public void nombrePresenteIgualDiferente_igual() {
        assertFalse(EditorialValidations.nombrePresenteIgualDiferente("test", "test"));
    }

    @Test
    public void nombrePresenteIgualDiferente_diferente() {
        assertTrue(EditorialValidations.nombrePresenteIgualDiferente("test", "no_test"));
    }
}