package com.magnet;

public class Cajero extends Usuario{
    FacturacionDB factura = new FacturacionDB();
    public Cajero(int idUsuario,String usuario, String contraseña, boolean estado, int funcion) {
        super(idUsuario, usuario, contraseña, estado, funcion);
    }


}
