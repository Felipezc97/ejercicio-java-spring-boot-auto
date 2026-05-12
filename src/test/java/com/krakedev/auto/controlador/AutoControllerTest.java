package com.krakedev.auto.controlador;

import com.krakedev.auto.entidad.Auto;
import com.krakedev.auto.servicio.AutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class AutoControllerTest {

    private AutoController autoController;
    private AutoService autoService;

    @BeforeEach
    void setUp() {
        // Inicializamos el servicio y el controlador
        autoService = new AutoService();
        autoController = new AutoController(autoService);
    }

    @Test
    void testControllerCrearYListar() {
        Auto a = new Auto("PDF-444", "Kia", "Gris");
        Auto creado = autoController.crear(a);
        
        assertNotNull(creado);
        ArrayList<Auto> lista = autoController.listar();
        assertEquals(1, lista.size());
    }

    @Test
    void testControllerBuscarPorPlaca() {
        autoController.crear(new Auto("EXT-999", "Ford", "Negro"));
        Auto encontrado = autoController.buscarPorPlaca("EXT-999");
        
        assertNotNull(encontrado);
        assertEquals("Ford", encontrado.getMarca());
    }

    @Test
    void testControllerActualizar() {
        autoController.crear(new Auto("ABC-000", "Renault", "Blanco"));
        Auto nuevosDatos = new Auto("ABC-000", "Renault", "Amarillo");
        
        Auto actualizado = autoController.actualizar("ABC-000", nuevosDatos);
        
        assertNotNull(actualizado);
        assertEquals("Amarillo", actualizado.getColor());
    }

    @Test
    void testControllerEliminar() {
        autoController.crear(new Auto("DEL-111", "Suzuki", "Azul"));
        boolean eliminado = autoController.eliminar("DEL-111");
        
        assertTrue(eliminado);
        assertEquals(0, autoController.listar().size());
    }

    @Test
    void testControllerBuscarNoExistente() {
        Auto noEncontrado = autoController.buscarPorPlaca("999-999");
        assertNull(noEncontrado);
    }
}