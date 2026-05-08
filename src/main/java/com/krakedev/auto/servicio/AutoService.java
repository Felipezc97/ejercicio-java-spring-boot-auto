package com.krakedev.auto.servicio;

import com.krakedev.auto.entidad.Auto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AutoService {

    private final ArrayList<Auto> autos = new ArrayList<>();

    public Auto crearAuto(Auto auto) {
        for (Auto a : autos) {
            if (a.getPlaca().equals(auto.getPlaca())) {
                return null;
            }
        }
        autos.add(auto);
        return auto;
    }

    public ArrayList<Auto> listar() {
        return autos;
    }

    public Auto buscarPorPlaca(String placa) {
        for (Auto a : autos) {
            if (a.getPlaca().equals(placa)) {
                return a;
            }
        }
        return null;
    }

    public Auto actualizar(String placa, Auto autoActualizado) {
        for (Auto a : autos) {
            if (a.getPlaca().equals(placa)) {
                a.setMarca(autoActualizado.getMarca());
                a.setColor(autoActualizado.getColor());
                return a;
            }
        }
        return null;
    }

    public boolean eliminar(String placa) {
        for (Auto a : autos) {
            if (a.getPlaca().equals(placa)) {
                return autos.remove(a);
            }
        }
        return false;
    }
}