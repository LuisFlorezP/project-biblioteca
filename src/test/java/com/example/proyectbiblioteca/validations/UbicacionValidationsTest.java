package com.example.proyectbiblioteca.validations;

import com.example.proyectbiblioteca.entities.Ubicacion;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class UbicacionValidationsTest {

    @Test
    public void ubicacionPresente_present() {
        assertTrue(UbicacionValidations.ubicacionPresente(Optional.of(new Ubicacion())));
    }

    @Test
    public void ubicacionPresente_empty() {
        assertFalse(UbicacionValidations.ubicacionPresente(Optional.empty()));
    }

    @Test
    public void atributoPresente_NO_vacio() {
        assertFalse(UbicacionValidations.atributoPresente("a"));
    }

    @Test
    public void atributoPresente_nulo() {
        assertTrue(UbicacionValidations.atributoPresente(null));
    }

    @Test
    public void atributoPresente_vacio() {
        assertTrue(UbicacionValidations.atributoPresente(""));
    }
}