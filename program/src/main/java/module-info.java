module com.magnet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.magnet to javafx.fxml;
    exports com.magnet;
}
