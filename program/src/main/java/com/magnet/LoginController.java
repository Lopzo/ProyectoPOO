package com.magnet;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField contrasenaField;

    @FXML
    private void personalButtonClick() {
        // Aquí puedes agregar la lógica para verificar el usuario y la contraseña
        // Puedes comparar los valores con algún conjunto predefinido o verificar en una base de datos
        // Por ahora, simplemente imprimimos los valores para demostración
        System.out.println("Usuario: " + usuarioField.getText());
        System.out.println("Contraseña: " + contrasenaField.getText());

        // Puedes agregar más lógica aquí según tus necesidades
    }
    @FXML
    private void volverAInicio() throws IOException {
        // Lógica para volver a la vista de inicio
        App.setRoot("Inicio");
    }
}
