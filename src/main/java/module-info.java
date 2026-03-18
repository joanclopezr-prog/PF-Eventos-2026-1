module org.example.pfeventos20261 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.pfeventos20261 to javafx.fxml;
    exports org.example.pfeventos20261;
}