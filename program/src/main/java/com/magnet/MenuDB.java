package com.magnet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDB {

    // Método para añadir un plato al menú
    public String agregarPlato(Plato plato) {
        Connection connection = null;
        try  
        {
            connection = ConexionBD.obtenerConexion();
            String sql = "INSERT INTO [MealMagnet].[dbo].[Menu] ([Plato],[Precio], [Receta],[Disponible]) VALUES (?, ?, ?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, plato.getPlato());
                preparedStatement.setDouble(2, plato.getPrecio());
                preparedStatement.setString(3, plato.getReceta());
                preparedStatement.setBoolean(4, plato.getDisponible());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al guardar el elemento";
        }
        finally
        {
            ConexionBD.cerrarConexion(connection);    
        }
        return "El plato se agrado con exito";
    }

    public String modificarPlato(Plato plato) {
        Connection connection = null;
        try {
            connection = ConexionBD.obtenerConexion();
            String sql = "UPDATE [MealMagnet].[dbo].[Menu] SET [Plato] = ?, [Precio] = ?, [Receta] = ?, [Disponible] = ? WHERE [idPlato] = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, plato.getPlato());
                preparedStatement.setDouble(2, plato.getPrecio());
                preparedStatement.setString(3, plato.getReceta());
                preparedStatement.setBoolean(4, plato.getDisponible());
                preparedStatement.setInt(5, plato.getNumPlato()); 
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al modificar el plato";
        } finally {
            ConexionBD.cerrarConexion(connection);
        }
        return "El plato se modificó con éxito";
    }

    public List<Plato> obtenerMenu() {

        List<Plato> menu = new ArrayList<>();

        Connection connection = null;

        try 
        {
            connection = ConexionBD.obtenerConexion();
        
            String sql = "SELECT * FROM Menu";
        
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {                       
                        int numPlato = resultSet.getInt("idPlato");
                        String nombrePlato = resultSet.getString("Plato");
                        double precioPlato = resultSet.getDouble("Precio");
                        String reseta = resultSet.getString("Receta");
                        boolean disponible = resultSet.getBoolean("Disponible");
                        Plato plato = new Plato(numPlato,nombrePlato, precioPlato, reseta, disponible);
                        menu.add(plato);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        } finally {
            // Cerrar la conexión fuera del bloque try-with-resources
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Manejar la excepción según tus necesidades
                }
            }
        }
        
        return menu;        
    }
    
    public List<Plato> obtenerClienteMenu()
    {
            return obtenerMenu();
    }


    public Plato obtenerPlato(int id) {
        Plato plato = null;
        Connection connection = null;
    
        try {
            connection = ConexionBD.obtenerConexion();
    
            String sql = "SELECT * FROM Menu WHERE Id = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int numPlato = resultSet.getInt("idPlato");
                        String nombrePlato = resultSet.getString("Plato");
                        double precioPlato = resultSet.getDouble("Precio");
                        String receta = resultSet.getString("Receta");
                        boolean disponible = resultSet.getBoolean("Disponible");
                        plato = new Plato(numPlato,nombrePlato, precioPlato, receta, disponible);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        } finally {
            // Cerrar la conexión fuera del bloque try-with-resources
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Manejar la excepción según tus necesidades
                }
            }
        }
    
        return plato;
    }

    public void eliminarPlato(int idPlato) {
        try (Connection connection = ConexionBD.obtenerConexion()) {
            String sql = "DELETE FROM Menu WHERE idPlato = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idPlato);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
    }
}
