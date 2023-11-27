package com.magnet;

import java.util.ArrayList;
import java.util.List;

public class Mesero extends Usuario {
    MenuDB menu = new MenuDB();
    ManejoPedidosDB manejoPedidos = new ManejoPedidosDB();
    private List<Mesa> mesasAsignadas = new ArrayList<>();

    public Mesero(int idUsuario, String usuario, String contraseña, boolean estado, int funcion) {
        super(idUsuario, usuario, contraseña, estado, funcion);
    }

    public void asignarMesa(Mesa mesa) {
        mesasAsignadas.add(mesa);
        mesa.equals(this);
    }

    public List<Plato> obtenerMenu() {
        return menu.obtenerMenu();
    }

    public String generarPedido(Mesa mesa, Pedido pedidoNuevo) {
        if (mesasAsignadas.contains(mesa)) {
            return manejoPedidos.insertarPedido(pedidoNuevo);
        } else {
            return "Esta mesa no está asignada al mesero.";
        }
    }

    public String solicitarCancelacion(Mesa mesa, int idPedido) {
        if (mesasAsignadas.contains(mesa)) {
            manejoPedidos.cancelarPedido(idPedido);
            return "Se ha enviado la solicitud de cancelación";
        } else {
            return "Esta mesa no está asignada al mesero.";
        }
    }

    public String modificarEstadoPedido(Mesa mesa, int idPedido, String nuevoEstado) {
        if (mesasAsignadas.contains(mesa)) {
            manejoPedidos.modificarEstadoPedido(idPedido, nuevoEstado);
            return "Se ha modificado el estado del pedido";
        } else {
            return "Esta mesa no está asignada al mesero.";
        }
    }

    public List<Pedido> obtenerListaPedidos(Mesa mesa) {
        if (mesasAsignadas.contains(mesa)) {
            return manejoPedidos.obtenerListaPedidos();
        } else {
            return new ArrayList<>(); // Devuelve una lista vacía si la mesa no está asignada al mesero
        }
    }

    public List<Mesa> obtenerMesasAsignadas() {
        return mesasAsignadas;
    }
}







