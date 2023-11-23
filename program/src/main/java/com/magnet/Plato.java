package com.magnet;

public class Plato {

    private String plato;
    private double precio;
    private String receta;

    public Plato(String plato, double precio, String receta) {
        this.plato = plato;
        this.precio = precio;
        this.receta = receta;
    }

    // Getters y setters para los atributos

    public String getPlato() {
        return plato;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    // Otros métodos de la clase Plato, si es necesario
}

