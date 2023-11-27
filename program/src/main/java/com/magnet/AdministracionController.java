package com.magnet;

import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class AdministracionController {

    @FXML
    private TableView<Usuario> usuariosTableView;

    @FXML
    private TableColumn<Usuario, Integer> idColumn;

    @FXML
    private TableColumn<Usuario, String> usuarioColumn;

    @FXML
    private TableColumn<Usuario, String> contraseñaColumn;

    @FXML
    private TableColumn<Usuario, String> funcionColumn;

    @FXML
    private TableColumn<Usuario, String> estadoColumn;

    @FXML
    private Button agregarUsuarioButton;

    @FXML
    private Button editarUsuarioButton;

    @FXML
    private Button borrarUsuarioButton;

    @FXML
    private TableView<Mesa> mesasTableView;

    @FXML
    private TableColumn<Mesa, Integer> mesaIdColumn;

    @FXML
    private TableColumn<Mesa, String> numeroMesaColumn;

    @FXML
    private TableColumn<Mesa, String> disponibleColumn;

    @FXML
    private Button agregarMesaButton;

    @FXML
    private Button editarMesaButton;

    @FXML
    private Button borrarMesaButton;

    @FXML
    private TextField usuarioTextField;

    @FXML
    private TextField contraseñaTextField;

    @FXML
    private TextField numeroMesaTextField;

    @FXML
    private ChoiceBox<Integer> funcionTextField; // Se asume que el tipo de datos es String, ajusta según sea necesario

    @FXML
    private CheckBox estadoTextField;

    @FXML
    private CheckBox disponibleTextField;
    
    private Administrador administrador;

    @FXML
    private void initialize() {

        inicializarAdministrador();

        // Usuarios
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdUsuarioProperty().asObject());
        usuarioColumn.setCellValueFactory(cellData -> cellData.getValue().getUsuarioProperty());
        contraseñaColumn.setCellValueFactory(cellData -> cellData.getValue().getContraseñaProperty());
        funcionColumn.setCellValueFactory(cellData -> cellData.getValue().getFuncionProperty());
        estadoColumn.setCellValueFactory(cellData -> cellData.getValue().getEstadoProperty());

        // Mesas
        mesaIdColumn.setCellValueFactory(cellData -> cellData.getValue().getIdMesaProperty().asObject());
        numeroMesaColumn.setCellValueFactory(cellData -> cellData.getValue().getMesaNumProperty());
        disponibleColumn.setCellValueFactory(cellData -> cellData.getValue().getEstadoProperty());

        // Asociar métodos a los botones
        agregarUsuarioButton.setOnAction(event -> agregarUsuario());
        editarUsuarioButton.setOnAction(event -> editarUsuario());
        borrarUsuarioButton.setOnAction(event -> borrarUsuario());

        agregarMesaButton.setOnAction(event -> agregarMesa());
        editarMesaButton.setOnAction(event -> editarMesa());
        borrarMesaButton.setOnAction(event -> borrarMesa());
        cargarFunciones();
        cargarDatosMesas();
        cargarDatosUsuarios();
    }
    private void inicializarAdministrador() {
        administrador = Login.getAdministrador();
    }

    private void cargarDatosUsuarios() {
        List<Usuario> listaUsuarios = administrador.obtenerListaUsuarios();

        usuariosTableView.getItems().clear();

        usuariosTableView.getItems().addAll(listaUsuarios);
    }

    private void cargarDatosMesas()
    {
        List<Mesa> listaMesas = administrador.obtenerListaMesas();

        mesasTableView.getItems().clear();

        mesasTableView.getItems().addAll(listaMesas);
    } 

    private void cargarFunciones()
    {
        List<Funcion> listaDeFunciones = Funcion.listaFunciones();
        funcionTextField.getItems().addAll(listaDeFunciones.stream().map(Funcion::getNum).collect(Collectors.toList()));
        funcionTextField.setConverter(new StringConverter<Integer>() {
            @Override
            public String toString(Integer idFuncion) {
                Funcion funcion = listaDeFunciones.stream().filter(f -> f.getNum() == idFuncion).findFirst().orElse(null);
            return (funcion != null) ? funcion.getNombre() : "";
            }

            @Override
            public Integer fromString(String string) {
                // Implementa la conversión inversa si es necesario (de String a Integer)
                // En este caso, es probable que no necesites esta conversión en un ChoiceBox de Integer.
                // Devuelve null si no puedes realizar la conversión.
                return null;
            }
        });
    }

    private void agregarUsuario() {
        String usuario = usuarioTextField.getText();
        String contraseña = contraseñaTextField.getText();
        Integer funcion = funcionTextField.getValue();
        boolean estado = estadoTextField.isSelected();

        Usuario nuevoUsuario = administrador.agregarSegunFucion(usuario, contraseña, estado, funcion);

        // Llamar al método de Administrador para agregar el usuario
        administrador.agregarUsuario(nuevoUsuario);

        // Actualizar la tabla de usuarios
        cargarDatosUsuarios();
    }

    private void editarUsuario() {
        Usuario usuarioSeleccionado = usuariosTableView.getSelectionModel().getSelectedItem();
    
        if (usuarioSeleccionado != null) {
            // Obtener datos de los campos de texto y ChoiceBox
            String nuevoUsuario = usuarioTextField.getText();
            String nuevaContraseña = contraseñaTextField.getText();
            Integer nuevaFuncion = funcionTextField.getValue();
            boolean nuevoEstado = estadoTextField.isSelected();
    
            // Actualizar los datos del usuario con los datos de los campos de texto y ChoiceBox
            usuarioSeleccionado.setUsuario((nuevoUsuario != null) ? nuevoUsuario : usuarioSeleccionado.getUsuario());
            usuarioSeleccionado.setContraseña((nuevaContraseña != null) ? nuevaContraseña : usuarioSeleccionado.getContraseña());
    
            // Aquí debes convertir el id de la función al nombre de la función si es necesa
            usuarioSeleccionado.setFuncion((nuevaFuncion != null) ? nuevaFuncion : usuarioSeleccionado.getFuncion());
    
            usuarioSeleccionado.setEstado((nuevoEstado != usuarioSeleccionado.getEstado()) ? nuevoEstado : usuarioSeleccionado.getEstado());
    
            // Llamar al método de Administrador para editar el usuario
            administrador.editarUsuario(usuarioSeleccionado);
    
            // Actualizar la tabla de usuarios
            cargarDatosUsuarios();
        }
    
    }

    private void borrarUsuario() {
        // Obtener el usuario seleccionado en la tabla
        Usuario usuarioSeleccionado = usuariosTableView.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado != null) {
            // Obtener el ID del usuario
            int idUsuario = usuarioSeleccionado.getIdUsuario();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de borrado");
            alert.setHeaderText("¿Estás seguro de que deseas borrar este usuario?");
            alert.setContentText("Esta acción no se puede deshacer.");
            // Llamar al método de Administrador para borrar el usuario
            administrador.borrarUsuario(idUsuario);

            // Actualizar la tabla de usuarios
            cargarDatosUsuarios();
        }
    }

    private void agregarMesa() {
        String numeroMesa = numeroMesaTextField.getText();
        boolean disponible = disponibleTextField.isSelected();

        Mesa nuevaMesa = new Mesa(0,numeroMesa, disponible);

        // Llamar al método de Administrador para agregar la mesa
        administrador.agregarMesa(nuevaMesa);

        // Actualizar la tabla de mesas
        cargarDatosMesas();
    }

    private void editarMesa() {
        Mesa mesaSeleccionada = mesasTableView.getSelectionModel().getSelectedItem();

        if (mesaSeleccionada != null) {
            // Obtener datos de los campos de texto y CheckBox
            String nuevoNumeroMesa = numeroMesaTextField.getText();
            boolean nuevoDisponible = disponibleTextField.isSelected();

            // Actualizar los datos de la mesa con los datos de los campos de texto y CheckBox
            mesaSeleccionada.setMesaNum((nuevoNumeroMesa != null) ? nuevoNumeroMesa : mesaSeleccionada.getMesaNum());
            
            // Llamar al método de Administrador para editar la mesa
            administrador.editarMesa(mesaSeleccionada);

            // Actualizar la tabla de mesas
            cargarDatosMesas();
        }
    }

    private void borrarMesa() {
        // Obtener la mesa seleccionada en la tabla
        Mesa mesaSeleccionada = mesasTableView.getSelectionModel().getSelectedItem();

        if (mesaSeleccionada != null) {
            // Obtener el ID de la mesa
            int idMesa = mesaSeleccionada.getIdMesa();

            // Mostrar un diálogo de confirmación antes de borrar la mesa
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de borrado");
            alert.setHeaderText("¿Estás seguro de que deseas borrar esta mesa?");
            alert.setContentText("Esta acción no se puede deshacer.");

            // Esperar a que el usuario confirme
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                // Llamar al método de Administrador para borrar la mesa
                administrador.borrarMesa(idMesa);

                // Actualizar la tabla de mesas
                cargarDatosMesas();
             }
        }
    }

}
