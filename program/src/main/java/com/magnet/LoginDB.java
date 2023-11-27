package com.magnet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {
    public static Usuario consultarUsuarios(String usuario, String contrasena) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Usuario usuarioLog = null;
        try {
            connection = ConexionBD.obtenerConexion();

            // Consulta SQL con filtro por usuario, contraseña y estado=true
            String sql = "SELECT idUsuario, usuario, contraseña, estado, funcion FROM Usuarios WHERE usuario = ? AND contraseña = ? AND estado = ?";

            statement = connection.prepareStatement(sql);

            // Establecer parámetros en la consulta
            statement.setString(1, usuario);
            statement.setString(2, contrasena);
            statement.setBoolean(3, true);

            // Ejecutar la consulta
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idUsuario = resultSet.getInt("idUsuario");
                String NombreUsuario = resultSet.getString("usuario");
                String contraseña = resultSet.getString("contraseña");
                boolean estado = resultSet.getBoolean("estado");
                int funcion = resultSet.getInt("funcion");

                // Obtener los valores de las columnas
                switch (funcion) {
                    case 1: 
                        usuarioLog = new Administrador(idUsuario, NombreUsuario, contraseña, estado, funcion);
                        break;
                    case 2:
                        usuarioLog = new Cocinero(idUsuario, NombreUsuario, contraseña, estado, funcion);
                        break;
                    case 3:
                        usuarioLog = new Mesero(idUsuario, NombreUsuario, contraseña, estado, funcion);
                        break;
                    case 4:
                        usuarioLog = new Cajero(idUsuario, NombreUsuario, contraseña, estado, funcion);
                        break;
                    default:
                        usuarioLog = null;
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos en el bloque finally
                ConexionBD.cerrarConexion(connection);
        }
        return usuarioLog;
    }
}
