package com.magnet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Administrador {
    public void crearYGuardarEmpleado(int idEmpleado, String nombre, int tipoDocumento,
                                      String documento, Date fechaNacimiento, Date fechaIngreso,
                                      boolean estado, String cargo, double salario) {
        Empleado nuevoEmpleado = new Empleado(idEmpleado, nombre, tipoDocumento, documento,
                fechaNacimiento, fechaIngreso, estado, cargo, salario) 
            
        };

        guardarEmpleadoEnBaseDeDatos(nuevoEmpleado);
    }

    private void guardarEmpleadoEnBaseDeDatos(Empleado empleado) {
        try (Connection connection = ConexionBD.obtenerConexion()) {
            String sql = "INSERT INTO Empleados (idEmpleado, nombre, tipoDocumento, documento, " +
                    "fechaNacimiento, fechaIngreso, estado, cargo, salario) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, empleado.getIdEmpleado());
                preparedStatement.setString(2, empleado.getNombre());
                preparedStatement.setInt(3, empleado.getTipoDocumento());
                preparedStatement.setString(4, empleado.getDocumento());
                preparedStatement.setDate(5, new java.sql.Date(empleado.getFechaNacimiento().getTime()));
                preparedStatement.setDate(6, new java.sql.Date(empleado.getFechaIngreso().getTime()));
                preparedStatement.setBoolean(7, empleado.isEstado());
                preparedStatement.setString(8, empleado.getCargo());
                preparedStatement.setDouble(9, empleado.getSalario());

                preparedStatement.executeUpdate();

                System.out.println("Empleado guardado en la base de datos correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Empleado> consultarListaEmpleados() {
        List<Empleado> empleados = new ArrayList<>();

        try (Connection connection = ConexionBD.obtenerConexion()) {
            String sql = "SELECT * FROM Empleados";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("idEmpleado");
                        String nombre = resultSet.getString("nombre");
                        int tipoDocumento = resultSet.getInt("tipoDocumento");
                        String documento = resultSet.getString("documento");
                        Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                        Date fechaIngreso = resultSet.getDate("fechaIngreso");
                        boolean estado = resultSet.getBoolean("estado");
                        String cargo = resultSet.getString("cargo");
                        double salario = resultSet.getDouble("salario");

                        Empleado empleado = new Empleado(id, nombre, tipoDocumento, documento,
                                fechaNacimiento, fechaIngreso, estado, cargo, salario);
                        empleados.add(empleado);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }

    public Empleado consultarEmpleado(int idEmpleado) {
        Empleado empleado = null;

        try (Connection connection = ConexionBD.obtenerConexion()) {
            String sql = "SELECT * FROM Empleados WHERE idEmpleado = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idEmpleado);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String nombre = resultSet.getString("nombre");
                        int tipoDocumento = resultSet.getInt("tipoDocumento");
                        String documento = resultSet.getString("documento");
                        Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                        Date fechaIngreso = resultSet.getDate("fechaIngreso");
                        boolean estado = resultSet.getBoolean("estado");
                        String cargo = resultSet.getString("cargo");
                        double salario = resultSet.getDouble("salario");

                        empleado = new Empleado(idEmpleado, nombre, tipoDocumento, documento,
                                fechaNacimiento, fechaIngreso, estado, cargo, salario);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleado;
    }
}

