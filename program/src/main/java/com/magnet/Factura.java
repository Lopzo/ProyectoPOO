package com.magnet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Factura {
    private final StringProperty numeroFactura;
    private final StringProperty descripcion;
    private final StringProperty monto;

    public Factura(String numeroFactura, String descripcion, String monto) {
        this.numeroFactura = new SimpleStringProperty(numeroFactura);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.monto = new SimpleStringProperty(monto);
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

    public String getMonto() {
        return monto.get();
    }

    public StringProperty montoProperty() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto.set(monto);
    }
}