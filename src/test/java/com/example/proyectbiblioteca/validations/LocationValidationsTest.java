package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Location;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class LocationValidationsTest {

    @Test
    public void locationPresent_present() {
        assertTrue(LocationValidations.locationPresent(Optional.of(new Location())));
    }

    @Test
    public void locationPresent_empty() {
        assertFalse(LocationValidations.locationPresent(Optional.empty()));
    }

    @Test
    public void attributePresent_NOT_empty() {
        assertFalse(LocationValidations.attributePresent("a"));
    }

    @Test
    public void attributePresent_null() {
        assertTrue(LocationValidations.attributePresent(null));
    }

    @Test
    public void attributePresent_empty() {
        assertTrue(LocationValidations.attributePresent(""));
    }
}