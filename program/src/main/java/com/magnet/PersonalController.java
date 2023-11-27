package com.magnet;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class PersonalController {

    @FXML
    private TextField usuarioTextField;

    @FXML
    private PasswordField contrasenaPasswordField;

    @FXML
    private Button botonIngresar;

    @FXML
    private Button botonSalir;


    // Este método se llamará cuando se haga clic en el botón "Ingresar"
    @FXML
    private void clickIngresar(ActionEvent event) throws IOException {
        
         // Obtener el valor del campo de usuario
         String usuario = usuarioTextField.getText();
        
         // Obtener el valor del campo de contraseña
         String contrasena = contrasenaPasswordField.getText();

         Usuario usuarioLog  =Login.Ingresar(usuario, contrasena);

         switch (usuarioLog.getFuncion()) {
            case 1:
                Login.setAdministrador(usuarioLog);
                App.setRoot("Administrador");
                break;
            case 2:
                Login.setCocinero(usuarioLog);
                break;
            case 3:
                Login.setMesero(usuarioLog);
                break;
            case 4:
                Login.setCajero(usuarioLog);
                break;
         
            default:
                Login.errorUsuario();
                break;
         }
    }

    // Este método se llamará cuando se haga clic en el botón "Salir"
    @FXML
    private void clickSalir(ActionEvent event) {
        // Lógica para manejar el evento, puedes personalizar esto según tus necesidades.
        System.out.println("Botón Salir clicado");
    }

}
