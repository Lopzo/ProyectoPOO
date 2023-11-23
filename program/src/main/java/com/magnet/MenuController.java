package com.magnet;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class MenuController {
    Menu menu = new Menu();
   @FXML
    private TableView<Plato> tablaMenu;

    @FXML
    private TableColumn<Plato, String> columnaPlato = new TableColumn<>("Plato");

    @FXML
    private TableColumn<Plato, Double> columnaPrecio = new TableColumn<>("Precio");;

    @FXML
    private TableColumn<Plato, Boolean> columnaSeleccionar;

    // Lista de platos en el menú
    private ObservableList<Plato> menuObservable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        try{
            
            columnaPlato.setCellValueFactory(cellData -> cellData.getValue().platoProperty());
            columnaPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());

            // Configurar la columna de selección con un CheckBox
            columnaSeleccionar.setCellFactory(CheckBoxTableCell.forTableColumn(columnaSeleccionar));
            columnaSeleccionar.setCellValueFactory(new PropertyValueFactory<Plato, Boolean>("Seleccion"));
            
            List<Plato> datosMenu = menu.getMenu();
        
            menuObservable.addAll(datosMenu);

            tablaMenu.setItems(menuObservable);
            tablaMenu.getColumns().add(columnaSeleccionar);
        }
        catch (Exception ex) 
        {
            System.err.println(ex.getMessage());
        }

    }

    // Resto del código...

    // Método para obtener el menú
}
