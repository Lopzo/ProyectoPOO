package com.magnet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManejoPedidosDB {
     // Método para insertar el pedido en la base de datos
    public String insertarPedido(Pedido pedido) {
        Connection connection = null;
        try {
            connection = ConexionBD.obtenerConexion();
            // Insertar el pedido en la tabla Pedidos
            String insertPedidoQuery = "INSERT INTO Pedidos (Mesa, Estado) VALUES (?, ?)";
            try(PreparedStatement pstmt = connection.prepareStatement(insertPedidoQuery, Statement.RETURN_GENERATED_KEYS))
            {
                pstmt.setInt(1, pedido.getMesa());
                pstmt.setString(2, pedido.getEstado());
                pstmt.executeUpdate();

                // Obtener el ID generado para el pedido
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idPedido = generatedKeys.getInt(1);

                        // Insertar los detalles del pedido en la tabla DetallePedido
                        String insertDetalleQuery = "INSERT INTO DetallePedido (IdPedido, IdPlato) VALUES (?, ?)";
                        try (PreparedStatement detallePstmt = connection.prepareStatement(insertDetalleQuery)) {
                            for (Plato plato : pedido.getPlatos()) {
                                detallePstmt.setInt(1, idPedido);
                                detallePstmt.setInt(2, plato.getNumPlato());
                                detallePstmt.executeUpdate();
                            }
                        }
                    } else {
                        throw new SQLException("No se pudo obtener el ID del pedido.");
                    }
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return "Error en al generar el pedido";
        }
        finally
        {
            ConexionBD.cerrarConexion(connection);            
        }
        return "El pedido se a guardado con exito";
    }

    // Método para calcular el total del pedido
    public double calcularTotalPedido(List<Plato> platos) {
        double total = 0;
        for (Plato plato : platos) {
            total += plato.getPrecio();
        }
        return total;
    }

    public void cancelarPedido(int idPedido) {
        Connection connection = null;
        try 
        {
            connection = ConexionBD.obtenerConexion();
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

    public void modificarEstadoPedido(int idPedido, String nuevoEstado) {
        try (Connection connection = ConexionBD.obtenerConexion()) {
            String updateEstadoQuery = "UPDATE Pedidos SET Estado = ? WHERE IdPedido = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(updateEstadoQuery)) {
                pstmt.setString(1, nuevoEstado);
                pstmt.setInt(2, idPedido);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
    }

    public List<Pedido> obtenerListaPedidos() {
        List<Pedido> listaPedidos = new ArrayList<>();

        try (Connection connection = ConexionBD.obtenerConexion()) {
            String sql = "SELECT * FROM Pedidos";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int idPedido = resultSet.getInt("IdPedido");
                        int mesa = resultSet.getInt("Mesa");
                        String estado = resultSet.getString("Estado");

                        // Obtener detalles del pedido
                        List<Plato> platos = consultarPlatosDePedido(idPedido);

                        // Crear un objeto Pedido
                        Pedido pedido = new Pedido(mesa, platos, estado);

                        // Agregar el pedido a la lista
                        listaPedidos.add(pedido);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
        return listaPedidos;
    }


    public List<Plato> consultarPlatosDePedido(int idPedido) {
        List<Plato> platos = new ArrayList<>();
    
        try (Connection connection = ConexionBD.obtenerConexion()) {
            String sql = "SELECT Menu.* FROM DetallePedido " +
                         "INNER JOIN Menu ON DetallePedido.IdPlato = Menu.idPlato " +
                         "WHERE DetallePedido.IdPedido = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idPedido);
    
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int numPlato = resultSet.getInt("idPlato");
                        String nombrePlato = resultSet.getString("Plato");
                        double precioPlato = resultSet.getDouble("Precio");
                        String receta = resultSet.getString("Receta");
                        boolean disponible = resultSet.getBoolean("Disponible");

                        Plato plato = new Plato(numPlato, nombrePlato, precioPlato, receta, disponible);
    
                        // Agregar el plato a la lista
                        platos.add(plato);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
    
        return platos;
    }
}
