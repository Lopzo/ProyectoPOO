package com.magnet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministracionDB {
    public String insertarUsuario(Usuario usuario) {
        boolean administradorCreado = comprobacionNumeroAdministradores();
        
        if(administradorCreado == true)
        {
            return "No puede crear un administrador";       
        }
        else
        {
            String sql = "INSERT INTO Usuario (usuario, contraseña, estado, funcion) VALUES (?, ?, ?, ?)";

            Connection connection = null;

            try 
            {
                connection = ConexionBD.obtenerConexion();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, usuario.getUsuario());
                preparedStatement.setString(2, usuario.getContraseña());
                preparedStatement.setBoolean(3, usuario.getEstado());
                preparedStatement.setInt(4, usuario.getFuncion());

                preparedStatement.executeUpdate();
            
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar la excepción según tus necesidades
                return "Se a producido un error durante la ejecucion";
            } 
            finally
            {
                ConexionBD.cerrarConexion(connection);
            }
            return "El usuario se a creado con exito";
        }
    }

    public String editarUsuario(Usuario usuario) {
        String sql = "UPDATE NombreDeTuTabla SET usuario = ?,contraseña = ?, estado = ?, funcion = ? WHERE idusuario = ?";
        Connection connection = null;
        try {
            connection = ConexionBD.obtenerConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getUsuario());
            preparedStatement.setBoolean(2, usuario.getEstado());
            preparedStatement.setInt(3, usuario.getFuncion());
            preparedStatement.setString(4, usuario.getUsuario());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Se a producido un error durante la ejecucion";
        }
        finally
        {
            ConexionBD.cerrarConexion(connection);
        }
        return "El comando se a ejecutado correctamente";
    }

    // Método para borrar un usuario existente en la base de datos
    public String borrarUsuario(int idUsuario) {
        String sql = "DELETE FROM NombreDeTuTabla WHERE idUsuario = ?";
        Connection connection = null;
        try 
        {
            connection = ConexionBD.obtenerConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUsuario);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
            return "Error al borrar el usuario";
        }
        return "El usuario a sido borrado con exito";
    }

    // Método para obtener un usuario por su nombre de usuario
    public Usuario obtenerUsuario(int idUsuario) {
        String sql = "SELECT * FROM Usarios WHERE idUsuario = ?";
        Usuario usuario = null;
        Connection connection = null;
        try {
            connection = ConexionBD.obtenerConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUsuario);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String NombreUsuario = resultSet.getString("usuario");
                    String contraseña = resultSet.getString("contraseña");
                    boolean estado = resultSet.getBoolean("estado");
                    int funcion = resultSet.getInt("funcion");

                    switch (funcion) {
                        case 1: 
                            usuario = new Administrador(idUsuario, NombreUsuario, contraseña, estado, funcion);
                            break;
                        case 2:
                            usuario = new Cocinero(idUsuario, NombreUsuario, contraseña, estado, funcion);
                            break;
                        case 3:
                            usuario = new Mesero(idUsuario, NombreUsuario, contraseña, estado, funcion);
                            break;
                        case 4:
                            usuario = new Cajero(idUsuario, NombreUsuario, contraseña, estado, funcion);
                            break;
                        default:
                            usuario = null;
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
        finally
        {
            ConexionBD.cerrarConexion(connection);
        }

        return usuario;
    }

    // Método para obtener la lista de todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> listaUsuarios = new ArrayList<>();
        Usuario usuario = null;
        Connection connection = null;
        try 
        {
            connection = ConexionBD.obtenerConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idUsuario = resultSet.getInt("idUsuario");
                String NombreUsuario= resultSet.getString("usuario");
                String contraseña = resultSet.getString("contraseña");
                boolean estado = resultSet.getBoolean("estado");
                int funcion = resultSet.getInt("funcion");

                switch (funcion) {
                        case 1: 
                            usuario = new Administrador(idUsuario, NombreUsuario, contraseña, estado, funcion);
                            break;
                        case 2:
                            usuario = new Cocinero(idUsuario, NombreUsuario, contraseña, estado, funcion);
                            break;
                        case 3:
                            usuario = new Mesero(idUsuario, NombreUsuario, contraseña, estado, funcion);
                            break;
                        case 4:
                            usuario = new Cajero(idUsuario, NombreUsuario, contraseña, estado, funcion);
                            break;
                        case 5:
                            usuario = new Cliente(idUsuario, NombreUsuario, contraseña, estado, funcion);
                            break;
                        default:
                            usuario = null;
                            break;
                    }
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
        finally
        {
            ConexionBD.cerrarConexion(connection);
        }

        return listaUsuarios;
    }

    public boolean comprobacionNumeroAdministradores()
    {
        boolean validacion = false;
        String sql = "SELECT COUNT(*) as numero_registros FROM nombre_de_tabla WHERE funcion = 1;";
        Connection connection = null;
        try 
        {
            connection = ConexionBD.obtenerConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int numero_registros = resultSet.getInt("numero_registros");
            if(numero_registros >= 1)
            {
                validacion = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
        finally
        {
            ConexionBD.cerrarConexion(connection);
        }

        return validacion;
    }
}
