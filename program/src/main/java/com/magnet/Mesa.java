package com.magnet;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Mesa {
    private int idMesa;
    private String mesaNum;
    private boolean estado; 
    private Mesero meseroAsignado;

    public Mesa(int idMesa, String mesaNum, Boolean estado)
    {
        this.idMesa = idMesa;
        this.mesaNum = mesaNum;
        this.estado = estado;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getMesaNum() {
        return mesaNum;
    }

    public void setMesaNum(String mesaNum) {
        this.mesaNum = mesaNum;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setMeseroAsignado(Mesero mesero) {
        this.meseroAsignado = mesero;
    }

    public String getEstadoString(boolean estado)
    {
        String estadoString = "";
        if(estado == true)
        {
            estadoString = "Ocupada";
        }
        else
        {   
            estadoString = "Disponible";
        }

        return estadoString;
    }

     public SimpleIntegerProperty getIdMesaProperty() {
        return new SimpleIntegerProperty(idMesa);
    }

    public SimpleStringProperty getMesaNumProperty() {
        return new SimpleStringProperty(mesaNum);
    }

    public SimpleStringProperty getEstadoProperty() {
        String estadoString = getEstadoString(estado);
        return new SimpleStringProperty(estadoString);
    }
}
