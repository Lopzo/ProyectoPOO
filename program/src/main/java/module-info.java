module com.magnet {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.magnet to javafx.fxml;
    exports com.magnet;
}
