package com.magnet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Magnet - Inicio");

        Label titleLabel = new Label("Magnet Gestion y Orden");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Obtener el modo de depuración
        String debugMode = getParameters().getRaw().get(0);

        ///try {
            // Cargar y mostrar la vista correspondiente al modo de depuración
           
            switch (debugMode) {
                case "Restaurante_debug":
                    loadAndShowView("Personal");
                    break;
                case "Cliente_debug":
                    loadAndShowView("Cliente");
                    break;
                default:
                    loadAndShowView("Error");
                    break;
            }

            // Mostrar la ventana principal
            stage.setScene(scene);
            stage.show();
       /*  } catch (IOException e) {
            // Mostrar mensaje de error en la terminal
            
        }*/
    }

    private void loadAndShowView(String viewName) throws IOException {
        // Crear la escena principal
        scene = new Scene(loadFXML(viewName), 640, 480);
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