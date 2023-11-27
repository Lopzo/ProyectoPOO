package com.magnet;

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
    private Button primaryButton;

    @FXML
    private Button primaryButton1;

    // Este método se llamará cuando se haga clic en el botón "Ingresar"
    @FXML
    private void clickIngresar(ActionEvent event) {
        // Lógica para manejar el evento, puedes personalizar esto según tus necesidades.
        System.out.println("Botón Ingresar clicado");
    }

    // Este método se llamará cuando se haga clic en el botón "Salir"
    @FXML
    private void clickSalir(ActionEvent event) {
        // Lógica para manejar el evento, puedes personalizar esto según tus necesidades.
        System.out.println("Botón Salir clicado");
    }
}
