package com.magnet;

import java.util.List;

public class Cocinero extends Usuario {

    private MenuDB menu = new MenuDB();
    private ManejoPedidosDB pedidos = new ManejoPedidosDB();
    public Cocinero(int idUsuario, String usuario, String contraseña, boolean estado, int funcion) {
        super(idUsuario,usuario, contraseña, estado, funcion);
    }

    public void agregarPlato(String nombrePlato, double precio, String receta)
    {
        Boolean disponible = true;
        Plato NuevoPlato = new Plato(0,nombrePlato, precio, receta, disponible);
        menu.agregarPlato(NuevoPlato);
    }

    public List<Pedido> ObtenerListaPedidos()
    {
        return pedidos.obtenerListaPedidos();
    }

    public void prepararPedido(int idPedido, String estado)
    {
        pedidos.modificarEstadoPedido(idPedido, estado);
    }

    public List<Plato> ObtenerMenu()
    {
        List<Plato> platos = menu.obtenerMenu();

        return platos;
    }

    public Plato obtenerPlato(int idPlato)
    {
        Plato plato =  menu.obtenerPlato(idPlato);

        return plato;
    }

    public void eliminarPlato(int idPlato)
    {
        menu.eliminarPlato(idPlato);
    }
}
