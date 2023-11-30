package com.magnet;

import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pedido {
    private int idPedido;
    private int mesa;
    private List<Plato> platos;
    private String estado;
    private double Total;

    public Pedido(int idPedido,int mesa, List<Plato> platos, String estado) {
        this.idPedido = idPedido;
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
    
    public int getIdPedido()
    {
        return idPedido;
    }

    public void setIdPedido(int idPedido)
    {
        this.idPedido = idPedido;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido {");
        sb.append("Mesa: ").append(mesa);
        sb.append(", Platos: ").append(platos);
        sb.append(", Estado: ").append(estado);
        sb.append("}");
        return sb.toString();
    }

        // Propiedades adicionales sin alterar los atributos
    public SimpleIntegerProperty idPedidoProperty() {
        return new SimpleIntegerProperty(idPedido);
    }

    public SimpleIntegerProperty mesaProperty() {
        return new SimpleIntegerProperty(mesa);
    }

    public SimpleStringProperty estadoProperty() {
        return new SimpleStringProperty(estado);
    }

    public SimpleDoubleProperty totalProperty()
    {
        
        return new SimpleDoubleProperty(Total);
    }

}
