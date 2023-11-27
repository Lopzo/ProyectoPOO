package com.magnet;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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
    private TableColumn<Pedido, String> precioTotalColumn;

    @FXML
    private Label opcionesLabel;

    @FXML
    private Button verPedidoButton;

    @FXML
    private Button entregarCocineroButton;

    @FXML
    private Button entregarClienteButton;

    @FXML
    private TableView<Plato> detallePedidoTableView;

    @FXML
    private TableColumn<Plato, String> idDetalleColumn;

    @FXML
    private TableColumn<Plato, String> platoColumn;

    @FXML
    private TableColumn<Plato, String> precioColumn;
    
    private Mesero mesero;

    @FXML
    private void initialize() {
        inicializarMesero();
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idPedidoProperty().asObject());
        mesaColumn.setCellValueFactory(cellData -> cellData.getValue().mesaProperty().asObject());
        estadoColumn.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
        
        verPedidoButton.setOnAction(event -> verDetallesPedido());
        entregarCocineroButton.setOnAction(event -> entregarPedidoCocinero());
        entregarCocineroButton.setOnAction(event -> entregaPedidoCliente());

        cargarDatosPedido();
    }

    private void inicializarMesero()
    {
        mesero = Login.getMesero();
    };

      private void cargarDatosPedido()
    {
        List<Pedido> listaPedidos = mesero.obtenerListaPedidos(null);
        ObservableList<Pedido> pedidosObservable = FXCollections.observableArrayList(listaPedidos);
        pedidoTableView.getItems().clear();
        pedidoTableView.getItems().addAll(pedidosObservable);
    }

    private void cargarDatosDetallesPedido(Pedido pedido)
    {   
        List<Plato> listPlatos = pedido.getPlatos();

        detallePedidoTableView.getItems().clear();
        detallePedidoTableView.getItems().addAll(listPlatos);
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
        
    }

    private void entregaPedidoCliente()
    {

    }
}
