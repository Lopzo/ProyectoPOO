package com.magnet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {
   
    @FXML
    private Label labelMenu;

    @FXML
    private Label labelPedido;

    @FXML
    private Button finalizarButton;

    @FXML
    private TableView<Plato> tableView;

    @FXML
    private TableColumn<Plato, Integer> idColumn;

    @FXML
    private TableColumn<Plato, String> platoColumn;

    @FXML
    private TableColumn<Plato, Double> precioColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button finalizar;

    @FXML
    private ListView<String> listView;

    List<Plato> platos;
    MenuDB menu = new MenuDB();
    ManejoPedidosDB pedido = new ManejoPedidosDB();
    Pedido pedidod;

    @FXML
    private void initialize() {

        idColumn.setCellValueFactory(cellData -> cellData.getValue().numPedidoProperty().asObject());
        platoColumn.setCellValueFactory(cellData -> cellData.getValue().platoProperty());
        precioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());

        addButton.setOnAction(event ->   addPlatillo());
        finalizar.setOnAction(event -> mandarPedido());
        cargarDatosMenu();
    }


    private void addPlatillo() {
        // Lógica para agregar un platillo a la ListView
       Plato plato = tableView.getSelectionModel().getSelectedItem();
        List<Plato> platos = new ArrayList<>();  // Inicializar la lista antes de agregar elementos

        if (plato != null) {
            // Si el plato seleccionado no es nulo, agrégalo a la lista
            platos.add(plato);
        } else {
            // Manejar el caso en el que no se ha seleccionado ningún plato
            System.out.println("No se ha seleccionado ningún plato.");
        }
    }

     private void cargarDatosMenu()
    {
        List<Plato> listaPlatos = menu.obtenerClienteMenu();
        ObservableList<Plato> platosObservable = FXCollections.observableArrayList(listaPlatos);
        tableView.getItems().clear();
        tableView.getItems().addAll(platosObservable);
    } 

    private void mandarPedido()
    {
        pedidod =new Pedido(0, 2, platos, "Pedido");
        pedido.insertarPedido(pedidod);
    }

    private void updatePlatosTableView() {
        ObservableList<Plato> platos = FXCollections.observableArrayList(menu.obtenerClienteMenu());
        tableView.setItems(platos);
    }
    // Puedes agregar más métodos según sea necesario para interactuar con la interfaz de usuario
}
