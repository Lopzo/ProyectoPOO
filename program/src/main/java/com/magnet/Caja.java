import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Caja {

    public void crearYGuadarFactura(String numeroFactura, String descripcion, String monto) {
        Factura nuevaFactura = new Factura(numeroFactura, descripcion, monto);

        guardarFacturaEnBaseDeDatos(nuevaFactura);
    }


    private void guardarFacturaEnBaseDeDatos(Factura factura) {
        try (Connection connection = ConexionBD.obtenerConexion()) {
            String sql = "INSERT INTO Factura (numeroFactura, descripcion, monto) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, factura.getNumeroFactura());
                preparedStatement.setString(2, factura.getDescripcion());
                preparedStatement.setBigDecimal(3, new java.math.BigDecimal(factura.getMonto()));

                preparedStatement.executeUpdate();
                System.out.println("Factura guardada en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Factura> consultarFacturas() {
        List<Factura> facturas = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA)) {
            String sql = "SELECT * FROM Factura";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String numeroFactura = resultSet.getString("numeroFactura");
                        String descripcion = resultSet.getString("descripcion");
                        String monto = resultSet.getBigDecimal("monto").toString();

                        Factura factura = new Factura(numeroFactura, descripcion, monto);
                        facturas.add(factura);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facturas;
    }

}