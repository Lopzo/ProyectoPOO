package com.magnet;

import java.util.List;

public class Cocinero extends Usuario {

    private Menu menu = new Menu();
    public Cocinero(String usuario, String contraseña, boolean estado) {
        super(usuario, contraseña, estado);
    }

    public void agregarPlato(String nombrePlato, double precio, String receta)
    {
        Plato NuevoPlato = new Plato(0,nombrePlato, precio, receta);
        menu.agregarPlato(NuevoPlato);
    }

    public void ObtenerListaPedidos()
    {
        
    }

    public void manipularPedido(Pedido pedido)
    {
        
    }

    public List<Plato> ObtenerMenu()
    {
        List<Plato> platos = menu.getMenu();

        return platos;
    }
}
