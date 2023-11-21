package com.magnet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String JDBC_URL = "jdbc:sqlserver://FELIPE-OFICIAL:1433;database=MealMagnet";
    private static final String USUARIO = "supp";
    private static final String CONTRASENA = "12345";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USUARIO, CONTRASENA);
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}