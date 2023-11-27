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

    public Usuario agregarSegunFucion(String usuario, String contraseña, boolean estado, int funcion)
    {
        Usuario nuevoUsuario;
        switch (funcion) {
            case 1: 
                nuevoUsuario = new Administrador(0, usuario, contraseña, estado, funcion);
                break;
            case 2:
                nuevoUsuario = new Cocinero(0,usuario, contraseña, estado, funcion);
                break;
            case 3:
                nuevoUsuario = new Mesero(0,usuario, contraseña, estado, funcion);
                break;
            case 4:
                nuevoUsuario = new Cajero(0,usuario, contraseña, estado, funcion);
                break;
            default:
                nuevoUsuario = null;
                break;
        }

        return nuevoUsuario;
    }
}
