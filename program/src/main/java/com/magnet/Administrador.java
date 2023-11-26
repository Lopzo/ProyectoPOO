package com.magnet;

import java.util.List;

public class Administrador extends Usuario {
    AdministracionDB administracion = new AdministracionDB();

    public Administrador(int idUsuario,String usuario, String contraseña, boolean estado, int funcion) {
        super(idUsuario,usuario, contraseña, estado, funcion);
    }


    public String agregarUsuario(Usuario usuario)
    {
        return administracion.insertarUsuario(usuario);

    }

    public String editarUsuario(Usuario usuario)
    {
        return administracion.editarUsuario(usuario);

    }

    public String borrarUsuario(int idUsuario)
    {
        return administracion.borrarUsuario(idUsuario);
    }

    public Usuario obtenerUsuario(int idUsuario)
    {
        return administracion.obtenerUsuario(idUsuario);
    }

    public List<Usuario> obtenerListaUsuarios()
    {
        return administracion.obtenerTodosLosUsuarios();
    }
}
