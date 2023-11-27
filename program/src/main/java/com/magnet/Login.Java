package com.magnet;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Login {
    private static Administrador administrador;
    private static Cocinero cocinero;
    private static Cajero cajero;
    private static Mesero mesero;
    public static Usuario Ingresar(String usuario, String contraseña)
    {
        return LoginDB.consultarUsuarios(usuario, contraseña);
    }

    public static Alert errorUsuario()
    {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setContentText("El usuario no dispone de ninguna funcion.");
        return alerta;
    }
    
    public static void errorUsuarioIngreso()
    {
        Alert alerta = new Alert(AlertType.WARNING);    
        alerta.setTitle("Error de autenticacion");
        alerta.setHeaderText(null);
        alerta.setContentText("El usuario o contraseña son incorrectos.");
        alerta.showAndWait();
    }

    public static void setAdministrador(Usuario usuario)
    { 
        Login.administrador = new Administrador(usuario.getIdUsuario(), usuario.getUsuario(), usuario.getContraseña(), usuario.getEstado(), usuario.getFuncion());
    }
    public static Administrador getAdministrador() {
        return administrador;
    }

    public static void setCocinero(Usuario usuario) {
        Login.cocinero = new Cocinero(usuario.getIdUsuario(), usuario.getUsuario(),
                usuario.getContraseña(), usuario.getEstado(), usuario.getFuncion());
    }

    public static Cocinero getCocinero() {
        return cocinero;
    }

    public static void setCajero(Usuario usuario) {
        Login.cajero = new Cajero(usuario.getIdUsuario(), usuario.getUsuario(),
                usuario.getContraseña(), usuario.getEstado(), usuario.getFuncion());
    }

    public static Cajero getCajero() {
        return cajero;
    }

    public static void setMesero(Usuario usuario) {
        Login.mesero = new Mesero(usuario.getIdUsuario(), usuario.getUsuario(),
                usuario.getContraseña(), usuario.getEstado(), usuario.getFuncion());
    }

    public static Mesero getMesero() {
        return mesero;
    }
}
