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
    
    @Test
    void testBuscarPorPlacaInexistente() {
        // Buscamos una placa que nunca se agregó
        Auto encontrado = autoService.buscarPorPlaca("XYZ-9999");
        
        assertNull(encontrado, "Debería retornar null si la placa no existe");
    }

    @Test
    void testActualizarAutoExitoso() {
        // 1. Preparamos el escenario
        String placa = "ABC-1010";
        autoService.crearAuto(new Auto(placa, "Fiat", "Gris"));
        
        // 2. Creamos el objeto con los nuevos datos
        Auto datosNuevos = new Auto(placa, "Ferrari", "Rojo");
        
        // 3. Ejecutamos la actualización
        Auto actualizado = autoService.actualizar(placa, datosNuevos);
        
        assertNotNull(actualizado);
        assertEquals("Ferrari", actualizado.getMarca());
        assertEquals("Rojo", actualizado.getColor());
    }

    @Test
    void testActualizarAutoInexistente() {
        Auto datosNuevos = new Auto("999-999", "Audi", "Verde");
        
        // Intentamos actualizar un auto que no está en la lista
        Auto resultado = autoService.actualizar("999-999", datosNuevos);
        
        assertNull(resultado, "Debería retornar null si intentamos actualizar un auto que no existe");
    }

    @Test
    void testEliminarAutoInexistente() {
        boolean resultado = autoService.eliminar("NON-0000");
        
        assertFalse(resultado, "Debería retornar false si el auto a eliminar no existe");
    }

    @Test
    void testGettersYSettersEntidad() {
        Auto auto = new Auto();
        auto.setPlaca("TTE-111");
        auto.setMarca("Tesla");
        auto.setColor("Blanco");

        assertEquals("TTE-111", auto.getPlaca());
        assertEquals("Tesla", auto.getMarca());
        assertEquals("Blanco", auto.getColor());
        assertNotNull(auto.toString());
    }
}