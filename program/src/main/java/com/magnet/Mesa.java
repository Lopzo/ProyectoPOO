package com.magnet;

public class Mesa {
    private int idMesa;
    private String mesaNum;
    private boolean estado; 
    private Mesero meseroAsignado;

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

}
