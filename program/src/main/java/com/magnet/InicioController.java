package com.magnet;

import java.io.IOException;

import javafx.fxml.FXML;

public class InicioController {

    @FXML
    private void mostrarLogin() throws IOException {
        App.setRoot("Login");
    }

    @FXML
    private void mostrarCliente() throws IOException {
        // Agrega la lógica para mostrar la vista de Cliente
        // Por ahora, simplemente imprimimos un mensaje para demostración
        App.setRoot("Menu");
        // Puedes agregar más lógica aquí según tus necesidades
    }
}