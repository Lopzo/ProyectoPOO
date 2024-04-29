package com.magnet;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;

public class MeserosController{

    @FXML
    private TableView<Pedido> pedidoTableView;

    @FXML
    private TableColumn<Pedido, Integer> idColumn;

    @FXML
    private TableColumn<Pedido, Integer> mesaColumn;

    @FXML
    private TableColumn<Pedido, String> estadoColumn;

    @FXML
    private Label opcionesLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private Button verPedidoButton;

    @FXML
    private Button entregarCocineroButton;

    @FXML
    private Button entregarClienteButton;

    @FXML
    private TableView<Plato> detallePedidoTableView;

    @FXML
    private TableColumn<Plato, Integer> idDetalleColumn;

    @FXML
    private TableColumn<Plato, String> platoColumn;

    @FXML
    private TableColumn<Plato, Double> precioColumn;
    
    @FXML 
    private TextField totalField;

    private Mesero mesero;

    @FXML
    private void initialize() {
        inicializarMesero();

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idPedidoProperty().asObject());
        mesaColumn.setCellValueFactory(cellData -> cellData.getValue().mesaProperty().asObject());
        estadoColumn.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());

        idDetalleColumn.setCellValueFactory(cellData -> cellData.getValue().numPedidoProperty().asObject());
        platoColumn.setCellValueFactory(cellData -> cellData.getValue().platoProperty());
        precioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());

        verPedidoButton.setOnAction(event -> verDetallesPedido());
        entregarCocineroButton.setOnAction(event -> entregarPedidoCocinero());
        entregarClienteButton.setOnAction(event -> entregaPedidoCliente());

        cargarDatosPedidos();
    }

    private void inicializarMesero()
    {
        mesero = Login.getMesero();
    };

     
    private void cargarDatosPedidos()
    {
        List<Pedido> listaPedidos = mesero.obtenerListaPedidos();
        ObservableList<Pedido> pedidosObservable = FXCollections.observableArrayList(listaPedidos);
        pedidoTableView.getItems().clear();
        pedidoTableView.getItems().addAll(pedidosObservable);
    }

    private void cargarDatosDetallesPedido(Pedido pedido)
    {   
        List<Plato> listPlatos = pedido.getPlatos();
        
        double totalPedido = mesero.obtenerTotalPedido(listPlatos);
        totalField.setText(String.valueOf(totalPedido));
        ObservableList<Plato> platosObservable = FXCollections.observableArrayList(listPlatos);
        detallePedidoTableView.getItems().clear();
        detallePedidoTableView.setItems(platosObservable );
    }

    private void verDetallesPedido()
    {
        Pedido pedidoSeleccionado= pedidoTableView.getSelectionModel().getSelectedItem();
     
        if(pedidoSeleccionado != null)
        {
            cargarDatosDetallesPedido(pedidoSeleccionado);
        }
    } 

    private void entregarPedidoCocinero()
    {
        Pedido platoSeleccionado = pedidoTableView.getSelectionModel().getSelectedItem();

        mesero.modificarEstadoPedido(platoSeleccionado.getIdPedido(), "Preparando");
        updatePedidosTableView();
    }

    private void entregaPedidoCliente()
    {
        Pedido platoSeleccionado = pedidoTableView.getSelectionModel().getSelectedItem();

        mesero.modificarEstadoPedido(platoSeleccionado.getIdPedido(), "Entregado");
        updatePedidosTableView();
    }

    private void updatePedidosTableView()
    {
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList(mesero.obtenerListaPedidos());
        pedidoTableView.setItems(pedidos);
    }
}
