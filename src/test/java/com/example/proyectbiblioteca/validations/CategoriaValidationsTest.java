package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Categoria;
import org.junit.Test;
import java.util.Optional;
import static org.junit.Assert.*;

public class CategoriaValidationsTest {

    @Test
    public void categoriaPresente_present() {
        assertTrue(CategoriaValidations.categoriaPresente(Optional.of(new Categoria())));
    }

    @Test
    public void categoriaPresente_empty() {
        assertFalse(CategoriaValidations.categoriaPresente(Optional.empty()));
    }

    @Test
    public void verificarNombre_valido() {
        assertFalse(CategoriaValidations.verificarNombre("aaaaaaaa"));
    }

    @Test
    public void verificarNombre_nulo() {
        assertTrue(CategoriaValidations.verificarNombre(null));
    }

    @Test
    public void verificarNombre_vacio() {
        assertTrue(CategoriaValidations.verificarNombre(""));
    }

    @Test
    public void verificarDescripcionCategoria_valido_menos_de_256_caracteres() {
        assertFalse(CategoriaValidations.verificarDescripcionCategoria("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verificarDescripcionCategoria_invalido_mas_de_255_caracteres() {
        assertTrue(CategoriaValidations.verificarDescripcionCategoria("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verificarDescripcionCategoria_nulo() {
        assertTrue(CategoriaValidations.verificarDescripcionCategoria(null));
    }

    @Test
    public void verificarDescripcionCategoria_vacio() {
        assertFalse(CategoriaValidations.verificarDescripcionCategoria(""));
    }

    @Test
    public void verificarDescripcionCategoria() {
        assertFalse(CategoriaValidations.nombrePresenteIgualDiferente("test", "test"));
    }

    @Test
    public void nombrePresenteIgualDiferente() {
        assertTrue(CategoriaValidations.nombrePresenteIgualDiferente("test", "no_test"));
    }
}