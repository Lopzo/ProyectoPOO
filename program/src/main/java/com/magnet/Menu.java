package com.magnet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Plato> platos;

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

    public List<String> getMenuFromDatabase() {

        List<String> menu = new ArrayList<>();

        Connection connection = null;

        try {
            connection = ConexionBD.obtenerConexion();
        
            String sql = "SELECT Plato,  FROM Menu";
        
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        menu.add(resultSet.getString("Plato"));
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

    // Método para obtener el precio del menú
    /*public double getPrecio() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://tu_servidor;databaseName=MealMagnet", "tu_usuario", "tu_contraseña")) {
            String sql = "SELECT TOP (1) [Precio] FROM [MealMagnet].[dbo].[Menu]";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        this.precio = resultSet.getDouble("Precio");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }

        return this.precio;
    }*/


}
