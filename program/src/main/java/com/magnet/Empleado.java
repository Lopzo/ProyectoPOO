package com.magnet;

import java.util.Date;

// Clase abstracta Empleado
public abstract class Empleado {

    // Atributos
    private int idEmpleado;
    private String nombre;
    private int tipoDocumento;
    private String documento;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private boolean estado;

    // Constructor
    public Empleado(int idEmpleado, String nombre, int tipoDocumento, String documento,
                    Date fechaNacimiento, Date fechaIngreso, boolean estado) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
    }

    // Métodos getters y setters

    // Método abstracto para definir el cargo (debe implementarse en las subclases)
    public abstract String definirCargo();

    // Método para trabajar (ejemplo de polimorfismo)
    public void trabajar() {
        System.out.println("El empleado está trabajando.");
    }

    // Resto de métodos y toString

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", tipoDocumento=" + tipoDocumento +
                ", documento='" + documento + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaIngreso=" + fechaIngreso +
                ", estado=" + estado +
                '}';
    }
}