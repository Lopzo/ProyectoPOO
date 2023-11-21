import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterfazRestauranteFX{
    private TextField txtMesa, txtPlato;
    private Button btnAgregar, btnImprimir, btnReiniciar;
    private TableView<Pedido> tablaMenu;
    private ObservableList<Pedido> listaPedidos;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Interfaz de Restaurante");

        // Crear componentes
        txtMesa = new TextField();
        txtPlato = new TextField();
        btnAgregar = new Button("Agregar");
        btnImprimir = new Button("Imprimir");
        btnReiniciar = new Button("Reiniciar");

        // Configurar la tabla
        listaPedidos = FXCollections.observableArrayList();
        tablaMenu = new TableView<>(listaPedidos);
        TableColumn<Pedido, String> mesaCol = new TableColumn<>("Mesa");
        mesaCol.setCellValueFactory(new PropertyValueFactory<>("mesa"));
        TableColumn<Pedido, String> platoCol = new TableColumn<>("Plato");
        platoCol.setCellValueFactory(new PropertyValueFactory<>("plato"));
        tablaMenu.getColumns().addAll(mesaCol, platoCol);

        // Configurar acciones de los botones
        btnAgregar.setOnAction(event -> agregarPedido());
        btnImprimir.setOnAction(event -> imprimirPedido());
        btnReiniciar.setOnAction(event -> reiniciarInterfaz());

        // Configurar el diseño de la interfaz
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(new Label("Mesa:"), 0, 0);
        grid.add(txtMesa, 1, 0);
        grid.add(new Label("Plato:"), 0, 1);
        grid.add(txtPlato, 1, 1);
        grid.add(btnAgregar, 0, 2);
        grid.add(btnImprimir, 1, 2);
        grid.add(tablaMenu, 0, 3, 2, 1);
        grid.add(btnReiniciar, 0, 4);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void agregarPedido() {
        String mesa = txtMesa.getText();
        String plato = txtPlato.getText();

        if (!mesa.isEmpty() && !plato.isEmpty()) {
            Pedido pedido = new Pedido(mesa, plato);
            listaPedidos.add(pedido);
            txtMesa.clear();
            txtPlato.clear();
        } else {
            mostrarAlerta("Error", "Por favor, ingrese la mesa y el plato.");
        }
    }

    private void imprimirPedido() {
        // Puedes implementar la lógica para imprimir la tabla aquí
        // Por ejemplo, puedes recorrer la lista y mostrar la información en la consola
        for (Pedido pedido : listaPedidos) {
            System.out.println("Mesa: " + pedido.getMesa() + ", Plato: " + pedido.getPlato());
        }
    }

    private void reiniciarInterfaz() {
        txtMesa.clear();
        txtPlato.clear();
        listaPedidos.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}