package com.magnet;

public class Cocinero extends Usuario {

    private Menu menu;
    public Cocinero(String usuario, String contraseña, boolean estado) {
        super(usuario, contraseña, estado);
    }

    public void agregarPlato(String nombrePlato, double precio, String receta)
    {
        Plato NuevoPlato = new Plato(nombrePlato, precio, receta);
        menu.agregarPlato(NuevoPlato);
    }

    public void manipularPedido(Pedido pedido)
    {
        
    }
}
