package com.magnet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//explain
class Pedido {
    private final StringProperty mesa;
    private final StringProperty plato;
    private boolean estado;

    public Pedido(String mesa, String plato) {
        this.mesa = new SimpleStringProperty(mesa);
        this.plato = new SimpleStringProperty(plato);
    }

    public String getMesa() {
        return mesa.get();
    }

    public StringProperty mesaProperty() {
        return mesa;
    }

    public String getPlato() {
        return plato.get();
    }

    public StringProperty platoProperty() {
        return plato;
    }
}