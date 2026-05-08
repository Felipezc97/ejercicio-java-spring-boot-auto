package com.krakedev.auto.controlador;

import com.krakedev.auto.entidad.Auto;
import com.krakedev.auto.servicio.AutoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/autos")
public class AutoController {

    private final AutoService autoService;

    // Inyeccion de dependencia por constructor
    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    @PostMapping
    public Auto crear(@RequestBody Auto auto) {
        return autoService.crearAuto(auto);
    }

    @GetMapping
    public ArrayList<Auto> listar() {
        return autoService.listar();
    }

    @GetMapping("/{placa}")
    public Auto buscarPorPlaca(@PathVariable String placa) {
        return autoService.buscarPorPlaca(placa);
    }

    @PutMapping("/{placa}")
    public Auto actualizar(@PathVariable String placa, @RequestBody Auto auto) {
        return autoService.actualizar(placa, auto);
    }

    @DeleteMapping("/{placa}")
    public boolean eliminar(@PathVariable String placa) {
        return autoService.eliminar(placa);
    }
}