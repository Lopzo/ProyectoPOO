package com.magnet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ClienteController {

    @FXML
    private Label labelMenu;

    @FXML
    private Label labelPedido;

    @FXML
    private Button finalizarButton;

    @FXML
    private TableView<String> tableView;

    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<String>();

    @FXML
    private Button addButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private void initialize() {
        // Puedes realizar inicializaciones aquí si es necesario
        // Este método se llama automáticamente después de cargar el FXML

        choiceBox.getItems().addAll("Platillo 1", "Platillo 2", "Platillo 3");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void addPlatillo() {
        // Lógica para agregar un platillo a la ListView
        String selectedPlatillo = choiceBox.getValue();
        if (selectedPlatillo != null && !selectedPlatillo.isEmpty()) {
            listView.getItems().add(selectedPlatillo);
        }
    }

    // Puedes agregar más métodos según sea necesario para interactuar con la interfaz de usuario
}
