import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Date;

public class InterfazAdministradorFXController {

    @FXML
    private TextField idEmpleadoField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField tipoDocumentoField;

    // Otros campos y controles necesarios...

    @FXML
    private Button guardarEmpleadoButton;

    private Administrador administrador = new Administrador();

    @FXML
    private void initialize() {
        // Puedes realizar inicializaciones aquí si es necesario
    }

    @FXML
    private void handleGuardarEmpleado() {
        int idEmpleado = Integer.parseInt(idEmpleadoField.getText());
        String nombre = nombreField.getText();
        // Obtener otros valores de los campos...

        // Por ejemplo, asumimos que tipoDocumento es un entero
        int tipoDocumento = Integer.parseInt(tipoDocumentoField.getText());

        // Crear una fecha de ejemplo (puedes cambiar esto según tus necesidades)
        Date fechaNacimiento = new Date();
        Date fechaIngreso = new Date();

        // Otros campos...

        boolean estado = true;  // Ejemplo, puedes cambiar esto
        String cargo = "Empleado";  // Ejemplo, puedes cambiar esto
        double salario = 50000.0;  // Ejemplo, puedes cambiar esto

        administrador.crearYGuardarEmpleado(idEmpleado, nombre, tipoDocumento,
                "documentoEjemplo", fechaNacimiento, fechaIngreso, estado, cargo, salario);

        // Puedes realizar otras operaciones o mostrar mensajes aquí
        System.out.println("Empleado guardado.");
    }
}