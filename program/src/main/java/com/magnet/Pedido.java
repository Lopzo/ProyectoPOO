package com.magnet;

import java.sql.*;
import java.util.List;

public class Pedido {
    private int mesa;
    private List<Plato> platos;
    private String estado;

    public Pedido(int mesa, List<Plato> platos) {
        this.mesa = mesa;
        this.estado = "Solicitado";
        this.platos = platos;
    }

    // Método para insertar el pedido en la base de datos
    public void insertarPedido() {
        try (Connection connection = ConexionBD.obtenerConexion()) {
            // Insertar el pedido en la tabla Pedidos
            String insertPedidoQuery = "INSERT INTO Pedidos (Mesa, Estado) VALUES (?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertPedidoQuery, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, mesa);
                pstmt.setString(2, estado);
                pstmt.executeUpdate();

                // Obtener el ID generado para el pedido
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idPedido = generatedKeys.getInt(1);

                        // Insertar los detalles del pedido en la tabla DetallePedido
                        String insertDetalleQuery = "INSERT INTO DetallePedido (IdPedido, IdPlato) VALUES (?, ?)";
                        try (PreparedStatement detallePstmt = connection.prepareStatement(insertDetalleQuery)) {
                            for (Plato plato : platos) {
                                detallePstmt.setInt(1, idPedido);
                                detallePstmt.setInt(2, plato.getNumPlato()); // Supongamos que Plato tiene un método getIdPlato()
                                detallePstmt.executeUpdate();
                            }
                        }
                    } else {
                        throw new SQLException("No se pudo obtener el ID del pedido.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para calcular el total del pedido
    public double calcularTotalPedido() {
        double total = 0;
        for (Plato plato : platos) {
            total += plato.getPrecio(); // Supongamos que Plato tiene un método getPrecio()
        }
        return total;
    }

    public void cancelarPedido(int idPedido) {
        try (Connection connection = ConexionBD.obtenerConexion()) {
            // Desactivar el autocommit para gestionar la transacción
            connection.setAutoCommit(false);

            try {
                // Obtener el ID del pedido a cancelar

                // Eliminar detalles del pedido
                String deleteDetalleQuery = "DELETE FROM DetallePedido WHERE IdPedido = ?";
                try (PreparedStatement detallePstmt = connection.prepareStatement(deleteDetalleQuery)) {
                    detallePstmt.setInt(1, idPedido);
                    detallePstmt.executeUpdate();
                }

                // Eliminar el pedido
                String deletePedidoQuery = "DELETE FROM Pedidos WHERE IdPedido = ?";
                try (PreparedStatement pedidoPstmt = connection.prepareStatement(deletePedidoQuery)) {
                    pedidoPstmt.setInt(1, idPedido);
                    pedidoPstmt.executeUpdate();
                }

                // Confirmar la transacción
                connection.commit();
            } catch (SQLException e) {
                // En caso de error, realizar un rollback para deshacer la transacción
                connection.rollback();
                e.printStackTrace();
            } finally {
                // Restaurar el autocommit
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener el ID del último pedido realizado en esa mesa
    private int obtenerIdPedido(int idPedido) throws SQLException {

        try (Connection connection = ConexionBD.obtenerConexion()) {
            String selectIdQuery = "SELECT TOP 1 IdPedido FROM Pedidos WHERE Mesa = ? ORDER BY IdPedido DESC";

            try (PreparedStatement pstmt = connection.prepareStatement(selectIdQuery)) {
                pstmt.setInt(1, mesa);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        idPedido = resultSet.getInt("IdPedido");
                    }
                }
            }
        }

        return idPedido;
    }
}
