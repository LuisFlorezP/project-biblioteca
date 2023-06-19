package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Category;
import org.junit.Test;
import java.util.Optional;
import static org.junit.Assert.*;

public class CategoryValidationsTest {

    @Test
    public void categoryPresent_present() {
        assertTrue(CategoryValidations.categoryPresent(Optional.of(new Category())));
    }

    @Test
    public void categoryPresent_empty() {
        assertFalse(CategoryValidations.categoryPresent(Optional.empty()));
    }

    @Test
    public void verifyNombre_correct() {
        assertFalse(CategoryValidations.verifyNombre("aaaaaaaa"));
    }

    @Test
    public void verifyNombre_null() {
        assertTrue(CategoryValidations.verifyNombre(null));
    }

    @Test
    public void verifyNombre_empty() {
        assertTrue(CategoryValidations.verifyNombre(""));
    }

    @Test
    public void verifyDescripcionCategoria_correct_less_of_256_characters() {
        assertFalse(CategoryValidations.verifyDescripcionCategory("aaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verifyDescripcionCategoria_correct_of_255_characters() {
        assertFalse(CategoryValidations.verifyDescripcionCategory("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verifyDescripcionCategoria_invalid_mas_de_255_characters() {
        assertTrue(CategoryValidations.verifyDescripcionCategory("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verifyDescripcionCategoria_null() {
        assertTrue(CategoryValidations.verifyDescripcionCategory(null));
    }

    @Test
    public void verifyDescripcionCategoria_empty() {
        assertFalse(CategoryValidations.verifyDescripcionCategory(""));
    }

    @Test
    public void nombrePresentEqualDifferent_equal() {
        assertFalse(CategoryValidations.nombrePresentEqualDifferent("test", "test"));
    }

    @Test
    public void nombrePresentEqualDifferent_different() {
        assertTrue(CategoryValidations.nombrePresentEqualDifferent("test", "no_test"));
    }
}