package com.magnet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    // Método para añadir un plato al menú
    public void agregarPlato(Plato plato) {
        try (Connection connection = ConexionBD.obtenerConexion()) {
            String sql = "INSERT INTO [MealMagnet].[dbo].[Menu] ([Plato],[Precio], [Receta]) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, plato.getPlato());
                preparedStatement.setDouble(2, plato.getPrecio());
                preparedStatement.setString(3, plato.getReceta());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
    }

    public List<Plato> getMenu() {

        List<Plato> menu = new ArrayList<>();

        Connection connection = null;

        try {
            connection = ConexionBD.obtenerConexion();
        
            String sql = "SELECT * FROM Menu";
        
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {                       
                        int numPlato = resultSet.getInt("idPlato");
                        String nombrePlato = resultSet.getString("Plato");
                        double precioPlato = resultSet.getDouble("Precio");
                        String reseta = resultSet.getString("Receta");

                        Plato plato = new Plato(numPlato,nombrePlato, precioPlato, reseta);
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

    public Plato consultarPlatoPorId(int id) {
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
    
                        plato = new Plato(numPlato,nombrePlato, precioPlato, receta);
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

}
