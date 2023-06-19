package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.PublishingHouse;
import org.junit.Test;
import java.util.Optional;
import static org.junit.Assert.*;

public class PublishingHouseValidationsTest {

    @Test
    public void publishingHousePresent_present() {
        assertTrue(PublishingHouseValidations.publishingHousePresent(Optional.of(new PublishingHouse())));
    }

    @Test
    public void publishingHousePresent_empty() {
        assertFalse(PublishingHouseValidations.publishingHousePresent(Optional.empty()));
    }

    @Test
    public void verifyNombre_invalid_lees_of_2_characters() {
        assertTrue(PublishingHouseValidations.verifyNombre("a"));
    }

    @Test
    public void verifyNombre_correct_of_2_characters() {
        assertFalse(PublishingHouseValidations.verifyNombre("aa"));
    }

    @Test
    public void verifyNombre_correct_between_2_and_30_characters() {
        assertFalse(PublishingHouseValidations.verifyNombre("aaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verifyNombre_correct_of_30_characters() {
        assertFalse(PublishingHouseValidations.verifyNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verifyNombre_invalid_further_of_30_characters() {
        assertTrue(PublishingHouseValidations.verifyNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verifyNombre_null() {
        assertTrue(PublishingHouseValidations.verifyNombre(null));
    }

    @Test
    public void verifyNombre_empty() {
        assertTrue(PublishingHouseValidations.verifyNombre(""));
    }

    @Test
    public void verifyDescripcionEditorial_correct_less_of_300_characters() {
        assertFalse(PublishingHouseValidations.verifyDescripcionEditorial("aaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verifyDescripcionEditorial_correct_of_300_characters() {
        assertFalse(PublishingHouseValidations.verifyDescripcionEditorial("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verifyDescripcionEditorial_invalid_further_of_300_characters() {
        assertTrue(PublishingHouseValidations.verifyDescripcionEditorial("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void verifyDescripcionEditorial_null() {
        assertTrue(PublishingHouseValidations.verifyDescripcionEditorial(null));
    }

    @Test
    public void verifyDescripcionEditorial_empty() {
        assertFalse(PublishingHouseValidations.verifyDescripcionEditorial(""));
    }

    @Test
    public void nombrePresentEqualDifferent_equal() {
        assertFalse(PublishingHouseValidations.nombrePresentEqualDifferent("test", "test"));
    }

    @Test
    public void nombrePresentEqualDifferent_different() {
        assertTrue(PublishingHouseValidations.nombrePresentEqualDifferent("test", "no_test"));
    }
}