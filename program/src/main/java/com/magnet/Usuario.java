package com.magnet;

// Clase abstracta Empleado
public abstract class Usuario {

    // Atributos
    private String usuario;
    private String contraseña;
    private boolean estado;

    // Constructor
   public Usuario(String usuario, String contraseña, boolean estado) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.estado = estado;
    }

    public void setUsuario(String nuevoUsuario) {
        this.usuario = nuevoUsuario;
    }

    // Método para establecer una nueva contraseña
    public void setContraseña(String nuevaContraseña) {
        this.contraseña = nuevaContraseña;
    }
    public String getUsuario() {
        return this.usuario;
    }

    // Método para obtener la contraseña
    public String getContraseña() {
        return this.contraseña;
    }

    // Método abstracto para definir el cargo (debe implementarse en las subclases)
    public boolean verificarAcceso(String usuario, String contraseña) {
        return this.usuario.equals(usuario) && this.contraseña.equals(contraseña) && this.estado;
    }
}
