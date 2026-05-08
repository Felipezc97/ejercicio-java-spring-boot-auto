package com.krakedev.auto.entidad;

public class Auto {
	
	private String placa;
    private String marca;
    private String color;

    // Constructor vacio
    public Auto() {
    }

    // Constructor con parametros
    public Auto(String placa, String marca, String color) {
        this.placa = placa;
        this.marca = marca;
        this.color = color;
    }

    // Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
