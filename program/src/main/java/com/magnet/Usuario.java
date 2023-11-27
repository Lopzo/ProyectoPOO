package com.magnet;

import java.util.List;

import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

// Clase abstracta Empleado
public abstract class Usuario {

    // Atributo
    private int idUsuario;
    private String usuario;
    private String contraseña;
    private boolean estado;
    private int funcion;

    // Constructor
   public Usuario(int idUsuario,String usuario, String contraseña, boolean estado, int funcion) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.estado = estado;
        this.funcion = funcion;
    }

    public void setUsuario(String nuevoUsuario) {
        this.usuario = nuevoUsuario;
    }

    // Método para establecer una nueva contraseña
    public void setContraseña(String nuevaContraseña) {
        this.contraseña = nuevaContraseña;
    }

    public boolean setEstado(boolean estado)
    {
        return this.estado;
    }

    public int setFuncion(int funcion)
    {
        return this.funcion;
    }

    public String getUsuario() {
        return this.usuario;
    }

    // Método para obtener la contraseña
    public String getContraseña() {
        return this.contraseña;
    }

    public boolean getEstado()
    {
        return this.estado;
    }

    public int getFuncion()
    {
        return this.funcion;
    }

    public int getIdUsuario()
    {
        return this.idUsuario;
    }

    public SimpleIntegerProperty getIdUsuarioProperty()
    {
        
        return new SimpleIntegerProperty(idUsuario);
    }
    public SimpleStringProperty getUsuarioProperty() {
        return new SimpleStringProperty(usuario);
    }

    public SimpleStringProperty getContraseñaProperty() {
        return new SimpleStringProperty(contraseña);
    }

    public String getEstadoString(boolean estado)
    {
        String estadoString = "";
        if(estado == true)
        {
            estadoString = "Activo";
        }
        else
        {   
            estadoString = "Inactivo";
        }

        return estadoString;
    }

    public SimpleStringProperty getEstadoProperty() {
        String estadoString = getEstadoString(estado);
        
        return new SimpleStringProperty(estadoString);
    }

    public SimpleStringProperty getFuncionProperty() {
        
        String funcionString = Funcion.getFuncionString(funcion);
        return new SimpleStringProperty(funcionString);
    }
}
