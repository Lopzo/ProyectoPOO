package com.magnet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturacionDB {
    
    // Método para crear una nueva factura
    public String crearFactura(Factura factura) {
        Connection connection = null;
        try {
            connection = ConexionBD.obtenerConexion();
            String sql = "INSERT INTO [MealMagnet].[dbo].[Factura] ([numeroFactura], [descripcion], [monto]) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, factura.getNumeroFactura());
                preparedStatement.setString(2, factura.getDescripcion());
                preparedStatement.setDouble(3, factura.getMonto());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al crear la factura";
        } finally {
            ConexionBD.cerrarConexion(connection);
        }
        return "La factura se creó con éxito";
    }

    // Método para modificar una factura existente
    public String modificarFactura(Factura factura) {
        Connection connection = null;
        try {
            connection = ConexionBD.obtenerConexion();
            String sql = "UPDATE [MealMagnet].[dbo].[Factura] SET [descripcion] = ?, [monto] = ? WHERE [idFactura] = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, factura.getDescripcion());
                preparedStatement.setDouble(2, factura.getMonto());
                preparedStatement.setInt(3, factura.getIdFactura());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al modificar la factura";
        } finally {
            ConexionBD.cerrarConexion(connection);
        }
        return "La factura se modificó con éxito";
    }

    // Método para obtener una factura por su ID
    public Factura verFactura(int idFactura) {
        Factura factura = null;
        Connection connection = null;
        try {
            connection = ConexionBD.obtenerConexion();
            String sql = "SELECT * FROM [MealMagnet].[dbo].[Factura] WHERE [idFactura] = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idFactura);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String numeroFactura = resultSet.getString("numeroFactura");
                        String descripcion = resultSet.getString("descripcion");
                        double monto = resultSet.getDouble("monto");
                        factura = new Factura(idFactura, numeroFactura, descripcion, monto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        } finally {
            ConexionBD.cerrarConexion(connection);
        }
        return factura;
    }

    // Método para obtener la lista de todas las facturas
    public List<Factura> obtenerListaFacturas() {
        List<Factura> listaFacturas = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConexionBD.obtenerConexion();
            String sql = "SELECT * FROM [MealMagnet].[dbo].[Factura]";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int idFactura = resultSet.getInt("idFactura");
                        String numeroFactura = resultSet.getString("numeroFactura");
                        String descripcion = resultSet.getString("descripcion");
                        double monto = resultSet.getDouble("monto");
                        Factura factura = new Factura(idFactura, numeroFactura, descripcion, monto);
                        listaFacturas.add(factura);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        } finally {
            ConexionBD.cerrarConexion(connection);
        }
        return listaFacturas;
    }

    // Método para borrar una factura por su ID
    public void borrarFactura(int idFactura) {
        try (Connection connection = ConexionBD.obtenerConexion()) {
            String sql = "DELETE FROM [MealMagnet].[dbo].[Factura] WHERE [idFactura] = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idFactura);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
    }
}
