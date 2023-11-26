package com.magnet;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Plato {
    private final SimpleIntegerProperty numPlato;
    private final StringProperty plato;
    private final SimpleDoubleProperty precio;
    private final StringProperty receta;
    private final BooleanProperty seleccionado = new SimpleBooleanProperty(false);
    private final BooleanProperty disponible;

    public Plato(int numPlato,String plato, double precio, String receta,boolean disponible) {
        this.numPlato = new SimpleIntegerProperty(numPlato); 
        this.plato = new SimpleStringProperty(plato);
        this.precio = new SimpleDoubleProperty(precio);
        this.receta = new SimpleStringProperty(receta);
        this.disponible = new SimpleBooleanProperty(disponible);
    }

    // Getters y setters para los atributos

    public int getNumPlato()
    {
        return numPlato.get();
    }

    public String getPlato() {
        return plato.get();
    }

    public void setPlato(String plato) {
        this.plato.set(plato);
    }

    public StringProperty platoProperty() {
        return plato;
    }

    public double getPrecio() {
        return precio.get();
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public SimpleDoubleProperty precioProperty() {
        return precio;
    }

    public String getReceta() {
        return receta.get();
    }

    public void setReceta(String receta) {
        this.receta.set(receta);
    }

    public StringProperty recetaProperty() {
        return receta;
    }


    public boolean isSeleccionado() {
        return seleccionado.get();
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado.set(seleccionado);
    }
    
    public void setDisponible(boolean disponible) {
        this.disponible.set(disponible);
    }

    public boolean getDisponible()
    {
        return disponible.get();
    }

    public BooleanProperty disponibleProperty()
    {
        return disponible;
    }

    public BooleanProperty seleccionadoProperty() {
        return seleccionado;
    }

    public SimpleIntegerProperty numPedidoProperty()
    {

        return numPlato;
    }
}

