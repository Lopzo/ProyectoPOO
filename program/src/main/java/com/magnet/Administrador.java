package com.magnet;

public class Administrador extends Usuario {

    public Administrador(String usuario, String contraseña, boolean estado) {
        super(usuario, contraseña, estado);
    }

    public void acceder(String usuario, String contraseña)
    {
        boolean paso = verificarAcceso(usuario, contraseña);

        if(paso == true )
        {
            
        }

    } 

    private void guadarUsuario()
    {
        
    }
    private void cambiarCredenciales(Usuario usuario, String nuevoUsuario, String nuevaContraseña) {
        usuario.setUsuario(nuevoUsuario);
        usuario.setContraseña(nuevaContraseña);
    }

    // Método para solicitar y ver la información de todas las clases
    private void verInformacion() {
        // Puedes acceder a la información de las instancias de otras clases aquí
        // Por ejemplo, si tienes instancias de Cocinero, Mesa, Mesero, Caja, puedes acceder a sus datos y métodos.

        // Ejemplo:
        // Cocinero cocinero = new Cocinero("cocinero1", "cocina123", true);
        // System.out.println("Información del cocinero: " + cocinero.getUsuario() + ", " + cocinero.getContraseña());
        // ... (hacer lo mismo para las otras clases)
    }

    // Método para verificar las credenciales del administrador
    private boolean verificarCredenciales(String usuario, String contraseña) {
        return this.getUsuario().equals(usuario) && this.getContraseña().equals(contraseña);
    }
}

