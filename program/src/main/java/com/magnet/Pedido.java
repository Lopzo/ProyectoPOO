package com.magnet;

import java.util.List;

public class Pedido {
    private int mesa;
    private List<Plato> platos;
    private String estado;

    public Pedido(int mesa, List<Plato> platos, String estado) {
        this.mesa = mesa;
        this.estado = estado;
        this.platos = platos;
    }

    // Getter y Setter para la propiedad mesa
       public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    // Getter y Setter para la propiedad platos
    public List<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(List<Plato> platos) {
        this.platos = platos;
    }

    // Getter y Setter para la propiedad estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
