package com.magnet;

class Cocinero extends Empleado {
    private String especialidad;

    public Cocinero(int idEmpleado, String nombre, int tipoDocumento, String documento,
                    Date fechaNacimiento, Date fechaIngreso, boolean estado, String especialidad) {
        super(idEmpleado, nombre, tipoDocumento, documento, fechaNacimiento, fechaIngreso, estado);
        this.especialidad = especialidad;
    }

    @Override
    public String definirCargo() {
        return "Cocinero";
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
