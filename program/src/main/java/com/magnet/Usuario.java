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

    // Métodos getters y setters

    // Método abstracto para definir el cargo (debe implementarse en las subclases)
    public boolean verificarAcceso(String usuario, String contraseña) {
        return this.usuario.equals(usuario) && this.contraseña.equals(contraseña) && this.estado;
    }
}
