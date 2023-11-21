package com.magnet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Pagina principal");

        // Crear etiqueta con el texto
        Label titleLabel = new Label("Magnet Gestion y Orden");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Crear contenedor y añadir la etiqueta
        VBox vbox = new VBox(20); // Espaciado vertical entre los nodos
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(titleLabel);
        vbox.setPadding(new Insets(20)); // Añadir un espacio alrededor del contenedor


        Button btnAdministrador = new Button("Administrador");
        Button btnCaja = new Button("Caja");
        Button btnRecibidoPedidos = new Button("Recibido de Pedidos");
    
        // Configurar acciones de los botones
        btnAdministrador.setOnAction(e -> {
            // Lógica para la opción de administrador
            try {
                App.setRoot("InterfazAdministradorFXController");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
    
        btnCaja.setOnAction(e -> {
            // Lógica para la opción de caja
            System.out.println("Opción de Caja seleccionada");
        });
    
        btnRecibidoPedidos.setOnAction(e -> {
            // Lógica para la opción de recibido de pedidos
            System.out.println("Opción de Recibido de Pedidos seleccionada");
        });
    
        // Crear contenedor y añadir botones
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(btnAdministrador, btnCaja, btnRecibidoPedidos);
    
        
        // Crear la escena principal
        scene = new Scene(vbox, 640, 480);
        stage.setScene(scene);
    
        // Mostrar la ventana principal
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    } 

    public static void main(String[] args) {
        launch(args);
    }

}