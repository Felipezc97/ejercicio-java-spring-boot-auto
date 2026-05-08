package com.krakedev.auto.servicio;

import com.krakedev.auto.entidad.Auto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AutoServiceTest {

    private AutoService autoService;

    @BeforeEach
    void setUp() {
        // Inicializamos el servicio antes de cada prueba
        autoService = new AutoService();
    }

    @Test
    void testCrearAutoExitoso() {
        Auto nuevoAuto = new Auto("ABC-1234", "Toyota", "Azul");
        Auto resultado = autoService.crearAuto(nuevoAuto);

        assertNotNull(resultado, "El auto debería haber sido creado");
        assertEquals("ABC-1234", resultado.getPlaca());
        assertEquals(1, autoService.listar().size());
    }

    @Test
    void testCrearAutoDuplicado() {
        Auto auto1 = new Auto("ABC-1234", "Toyota", "Azul");
        Auto auto2 = new Auto("ABC-1234", "Hyundai", "Rojo");

        autoService.crearAuto(auto1);
        Auto resultado = autoService.crearAuto(auto2);

        assertNull(resultado, "No debería permitir crear un auto con placa duplicada");
        assertEquals(1, autoService.listar().size());
    }

    @Test
    void testListarAutosVacio() {
        ArrayList<Auto> lista = autoService.listar();
        assertTrue(lista.isEmpty(), "La lista debería iniciar vacía");
    }

    @Test
    void testBuscarPorPlacaExistente() {
        String placa = "PBC-5555";
        autoService.crearAuto(new Auto(placa, "Nissan", "Negro"));

        Auto encontrado = autoService.buscarPorPlaca(placa);

        assertNotNull(encontrado);
        assertEquals("Nissan", encontrado.getMarca());
    }

    @Test
    void testEliminarAuto() {
        String placa = "GHJ-9090";
        autoService.crearAuto(new Auto(placa, "Ford", "Blanco"));
        
        boolean eliminado = autoService.eliminar(placa);
        
        assertTrue(eliminado, "El auto debería haber sido eliminado");
        assertEquals(0, autoService.listar().size());
    }
}