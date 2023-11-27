package com.magnet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesasDB {

    // ... Otros métodos de la clase ...

    public String insertarMesa(Mesa mesa) {
        String sql = "INSERT INTO Mesa (Mesa, Estado) VALUES (?, ?)";
        Connection connection = null;

        try {
            connection = ConexionBD.obtenerConexion();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, mesa.getMesaNum());
                preparedStatement.setBoolean(2, mesa.isEstado());
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    return "Mesa insertada correctamente.";
                } else {
                    return "No se pudo insertar la mesa.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Se ha producido un error durante la ejecución.";
        } finally {
            ConexionBD.cerrarConexion(connection);
        }
    }

    public String editarMesa(Mesa mesa) {
        String sql = "UPDATE Mesa SET Estado = ? WHERE idMesa = ?";
        Connection connection = null;

        try {
            connection = ConexionBD.obtenerConexion();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setBoolean(1, mesa.isEstado());
                preparedStatement.setInt(2, mesa.getIdMesa());
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    return "Mesa actualizada correctamente.";
                } else {
                    return "No se pudo actualizar la mesa.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Se ha producido un error durante la ejecución.";
        } finally {
            ConexionBD.cerrarConexion(connection);
        }
    }

    public String borrarMesa(int idMesa) {
        String sql = "DELETE FROM Mesa WHERE idMesa = ?";
        Connection connection = null;

        try {
            connection = ConexionBD.obtenerConexion();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idMesa);
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    return "Mesa eliminada correctamente.";
                } else {
                    return "No se pudo eliminar la mesa.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Se ha producido un error durante la ejecución.";
        } finally {
            ConexionBD.cerrarConexion(connection);
        }
    }

    public Mesa obtenerMesa(int idMesa) {
        String sql = "SELECT * FROM Mesa WHERE idMesa = ?";
        Connection connection = null;
        Mesa mesa = null;

        try {
            connection = ConexionBD.obtenerConexion();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idMesa);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        mesa = new Mesa();
                        mesa.setIdMesa(resultSet.getInt("idMesa"));
                        mesa.setMesaNum(resultSet.getString("Mesa"));
                        mesa.setEstado(resultSet.getBoolean("Estado"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion(connection);
        }

        return mesa;
    }

    public List<Mesa> obtenerTodasLasMesas() {
        String sql = "SELECT * FROM Mesa";
        Connection connection = null;
        List<Mesa> listaMesas = new ArrayList<>();

        try {
            connection = ConexionBD.obtenerConexion();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Mesa mesa = new Mesa();
                    mesa.setIdMesa(resultSet.getInt("idMesa"));
                    mesa.setMesaNum(resultSet.getString("Mesa"));
                    mesa.setEstado(resultSet.getBoolean("Estado"));
                    listaMesas.add(mesa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexionBD.cerrarConexion(connection);
        }

        return listaMesas;
    }
}
