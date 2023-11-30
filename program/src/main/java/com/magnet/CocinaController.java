package com.magnet;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CocinaController {

    @FXML
    private TableView<Plato> platosTableView;

    @FXML
    private TableColumn<Plato, Integer> idColumn;

    @FXML
    private TableColumn<Plato, String> platoColumn;

    @FXML
    private TableColumn<Plato, Double> precioColumn;

    @FXML
    private TableColumn<Plato, String> recetaColumn;

    @FXML
    private TableColumn<Plato, Boolean> disponibleColumn;

    @FXML
    private Button agregarButton;

    @FXML
    private Button editarButton;

    @FXML
    private Button borrarButton;

    @FXML
    private TextField platoTextField;

    @FXML
    private TextField precioTextField;

    @FXML
    private TextArea recetaTextArea;

    @FXML
    private ChoiceBox<String> disponibleChoiceBox;

    @FXML
    private TableView<Pedido> pedidosTableView;

    @FXML
    private TableColumn<Pedido, Integer> idPedidoColumn;

    @FXML
    private TableColumn<Pedido, Integer> platoPedidoColumn;

    @FXML
    private TableColumn<Pedido, String> estadoPedidoColumn;

    @FXML
    private Button entregarButton;

    @FXML
    private Button verDetallesPedidoButton;

    @FXML 
    private TableView<Plato> detallesPedidoTableView;

    @FXML
    private TableColumn<Plato, Integer> idDetalleColumn;

    @FXML
    private TableColumn<Plato, String> detallePlatoColumn;

    @FXML
    private TableColumn<Plato, String> detalleResetaColumn;


    private Cocinero cocinero;  // Supongamos que tienes una instancia de Cocinero

    @FXML
    void initialize() {
        inicializarCocinero();
        // Mesas
        idColumn.setCellValueFactory(cellData -> cellData.getValue().numPedidoProperty().asObject());
        platoColumn.setCellValueFactory(cellData -> cellData.getValue().platoProperty());
        precioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        recetaColumn.setCellValueFactory(cellData -> cellData.getValue().recetaProperty());
        disponibleColumn.setCellValueFactory(cellData -> cellData.getValue().disponibleProperty());
    
        // Pedidos
        idPedidoColumn.setCellValueFactory(cellData -> cellData.getValue().idPedidoProperty().asObject());
        platoPedidoColumn.setCellValueFactory(cellData -> cellData.getValue().mesaProperty().asObject());
        estadoPedidoColumn.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
        
        // Detalles pedido
        idDetalleColumn.setCellValueFactory(cellData -> cellData.getValue().numPedidoProperty().asObject());
        detallePlatoColumn.setCellValueFactory(cellData -> cellData.getValue().platoProperty());
        detalleResetaColumn.setCellValueFactory(cellData -> cellData.getValue().recetaProperty());

        // Asociar métodos a los botones de Platos
        agregarButton.setOnAction(event -> agregarPlato());
        editarButton.setOnAction(event -> editarPlato());
        borrarButton.setOnAction(event -> borrarPlato());
    
        // Asociar métodos a los botones de Pedidos
        entregarButton.setOnAction(event -> entregarPedido());
        verDetallesPedidoButton.setOnAction(event -> verDetallesPedido());
        

        cargarDatosMenu();
        cargarDatosPedido();
    }

    private void inicializarCocinero()
    {
        cocinero = Login.getCocinero();
    }
    
    private void cargarDatosMenu()
    {
        List<Plato> listaPlatos = cocinero.ObtenerMenu();
        ObservableList<Plato> platosObservable = FXCollections.observableArrayList(listaPlatos);
        platosTableView.getItems().clear();
        platosTableView.getItems().addAll(platosObservable);
    } 

    private void cargarDatosPedido()
    {
        List<Pedido> listaPedidos = cocinero.ObtenerListaPedidos();
        ObservableList<Pedido> pedidosObservable = FXCollections.observableArrayList(listaPedidos);
        pedidosTableView.getItems().clear();
        pedidosTableView.getItems().addAll(pedidosObservable);
    }

    private void cargarDatosDetallesPedido(Pedido pedido)
    {   
        List<Plato> listPlatos = pedido.getPlatos();

        detallesPedidoTableView.getItems().clear();
        detallesPedidoTableView.getItems().addAll(listPlatos);
    }

    private void verDetallesPedido()
    {
        Pedido pedidoSeleccionado= pedidosTableView.getSelectionModel().getSelectedItem();
        if(pedidoSeleccionado != null)
        {
            cargarDatosDetallesPedido(pedidoSeleccionado);
        }
    } 
     

    private void agregarPlato() {
        // Lógica para agregar un nuevo plato
        String plato = platoTextField.getText();
        double precio = Double.parseDouble(precioTextField.getText());
        String receta = recetaTextArea.getText();

        cocinero.agregarPlato(plato, precio, receta);

        // Actualizar la tabla de platos
        updatePlatosTableView();
    }

    private void editarPlato() {
        // Lógica para editar un plato existente
        Plato platoSeleccionado = platosTableView.getSelectionModel().getSelectedItem();
        if (platoSeleccionado != null) {
            // Realizar las operaciones de edición aquí
        }
    }
    private void borrarPlato() {
        // Lógica para borrar un plato existente
        Plato platoSeleccionado = platosTableView.getSelectionModel().getSelectedItem();
        if (platoSeleccionado != null) {
            cocinero.eliminarPlato(platoSeleccionado.getNumPlato());

            // Actualizar la tabla de platos
            updatePlatosTableView();
        }
    }

    private void entregarPedido()
    {
        Pedido pedidoSeleccionado = pedidosTableView.getSelectionModel().getSelectedItem();   

        if(pedidoSeleccionado != null)
        {
            cocinero.prepararPedido(pedidoSeleccionado.getIdPedido(), "Preparado");
            updatePedidosTableView();
        }
    }

    // Método para actualizar la tabla de platos
    private void updatePlatosTableView() {
        ObservableList<Plato> platos = FXCollections.observableArrayList(cocinero.ObtenerMenu());
        platosTableView.setItems(platos);
    }
    
    private void updatePedidosTableView()
    {
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList(cocinero.ObtenerListaPedidos());
        pedidosTableView.setItems(pedidos);
    }
}
