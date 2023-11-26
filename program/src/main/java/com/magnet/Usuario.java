package com.magnet;

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

    public boolean SetEstado(boolean estado)
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
    // Método abstracto para definir el cargo (debe implementarse en las subclases)
    public boolean verificarAcceso(String usuario, String contraseña) {
        return this.usuario.equals(usuario) && this.contraseña.equals(contraseña) && this.estado;
    }
}
