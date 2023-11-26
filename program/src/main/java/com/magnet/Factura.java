package com.magnet;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Factura {
    private final SimpleIntegerProperty idFactura; 
    private final StringProperty numeroFactura;
    private final StringProperty descripcion;
    private final SimpleDoubleProperty monto;

    public Factura(int idFactura,String numeroFactura, String descripcion, Double monto) {
        this.idFactura = new SimpleIntegerProperty(idFactura);
        this.numeroFactura = new SimpleStringProperty(numeroFactura);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.monto = new SimpleDoubleProperty(monto);
    }

    public SimpleIntegerProperty simpleIntProperty()
    {
        return idFactura;
    }

    public int getIdFactura()
    {
        return idFactura.get();
    } 

    public String getNumeroFactura() {
        return numeroFactura.get();
    }

    public StringProperty numeroFacturaProperty() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura.set(numeroFactura);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public Double getMonto() {
        return monto.get();
    }

    public SimpleDoubleProperty montoProperty() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto.set(monto);
    }
}