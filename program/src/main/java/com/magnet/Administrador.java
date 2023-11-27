package com.magnet;

import java.util.List;

public class Administrador extends Usuario {
    AdministracionDB administracion = new AdministracionDB();
    MesasDB mesas = new MesasDB();

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

    public String agregarMesa(Mesa mesa) {
        return mesas.insertarMesa(mesa);
    }

    public String editarMesa(Mesa mesa) {
        return mesas.editarMesa(mesa);
    }

    public String borrarMesa(int idMesa) {
        return mesas.borrarMesa(idMesa);
    }

    public Mesa obtenerMesa(int idMesa) {
        return mesas.obtenerMesa(idMesa);
    }

    public List<Mesa> obtenerListaMesas() {
        return mesas.obtenerTodasLasMesas();
    }
    
    public boolean verificarAdministrador()
    {
        return administracion.comprobacionNumeroAdministradores();
    }
}
