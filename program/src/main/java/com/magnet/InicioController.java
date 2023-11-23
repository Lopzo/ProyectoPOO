package com.magnet;

import java.io.IOException;

import javafx.fxml.FXML;

public class InicioController {

    @FXML
    private void mostrarLogin() throws IOException {
        App.setRoot("Login");
    }
}