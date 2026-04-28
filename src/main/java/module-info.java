module org.example.pfeventos20261 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.base;
    opens org.example.pfeventos20261 to javafx.fxml;
    opens org.example.pfeventos20261.viewController.administrador to javafx.fxml;

//    opens org.example.pfeventos20261 to javafx.fxml;
    exports org.example.pfeventos20261;
}