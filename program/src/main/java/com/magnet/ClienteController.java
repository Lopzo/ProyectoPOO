package com.magnet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TableView<Plato> menuTableView;

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
    private TableView<Plato> pedidoTableView;

    @FXML
    private TableColumn<Plato,Integer> idPedidoPlatoColumn;

    @FXML
    private TableColumn<Plato,String> pedidoPlatoColumn;

    @FXML
    private TableColumn<Plato, Double> pedidoPrecioColumn;

    List<Plato> platos = new ArrayList<>();
    MenuDB menu;
    ManejoPedidosDB pedido;
    Pedido pedidod;

    @FXML
    private void initialize() {
        menu = new MenuDB();
        pedido = new ManejoPedidosDB();

      

        idColumn.setCellValueFactory(cellData -> cellData.getValue().numPedidoProperty().asObject());
        platoColumn.setCellValueFactory(cellData -> cellData.getValue().platoProperty());
        precioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());

        idPedidoPlatoColumn.setCellValueFactory(cellData -> cellData.getValue().numPedidoProperty().asObject());
        pedidoPlatoColumn.setCellValueFactory(cellData -> cellData.getValue().platoProperty());
        pedidoPrecioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());

        idPedidoPlatoColumn.setCellValueFactory(cellData -> cellData.getValue().numPedidoProperty().asObject());
        pedidoPlatoColumn.setCellValueFactory(cellData -> cellData.getValue().platoProperty());
        pedidoPrecioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());

        addButton.setOnAction(event -> addPlatillo());
        finalizar.setOnAction(event -> mandarPedido());

        cargarDatosMenu();
    }


    private void addPlatillo() {
        // Lógica para agregar un platillo a la ListView
        Plato plato = menuTableView.getSelectionModel().getSelectedItem();
        
        platos.add(plato);
        if (plato != null) {
            cargarPlatosPedido();
        } else {
            // Manejar el caso en el que no se ha seleccionado ningún plato
            System.out.println("No se ha seleccionado ningún plato.");
        }
    }

     private void cargarDatosMenu()
    {
        List<Plato> listaPlatos = menu.obtenerClienteMenu();
        ObservableList<Plato> platosObservable = FXCollections.observableArrayList(listaPlatos);
        menuTableView.getItems().clear();
        menuTableView.getItems().addAll(platosObservable);
    } 

    private void cargarPlatosPedido()
    {
        
        ObservableList<Plato> platosObservable = FXCollections.observableArrayList(platos);
        pedidoTableView.setItems(platosObservable);
    }

    private void mandarPedido()
    {
        pedidod =new Pedido(0, 2, platos, "Pedido");
        String mensaje = pedido.insertarPedido(pedidod);

        mensajeRegistro(mensaje);
        
    }

    public static void mensajeRegistro(String mensaje)
    {
        Alert alerta = new Alert(AlertType.WARNING);    
        alerta.setTitle("Resultado pedido");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    // Puedes agregar más métodos según sea necesario para interactuar con la interfaz de usuario
}
