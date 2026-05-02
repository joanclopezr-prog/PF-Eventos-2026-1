package org.example.pfeventos20261.viewController.administrador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import org.example.pfeventos20261.App;
import java.io.IOException;

public class DashboardAdminViewController {

    @FXML private Button btnGestionEventos;
    @FXML private BorderPane mainContent;
    @FXML private Button btnGestionRecintos;
    @FXML private Button btnGestionAsientos;
    @FXML private Button btnGestionUsuarios;
    @FXML private Button btnControlCompras;
    @FXML private Button btnIncidencias;
    @FXML private Button btnMetricas;
    @FXML private Button btnCerrarSesionAdmin;

    @FXML private AnchorPane contentAreaAdmin;

    @FXML
    private void initialize() {
        cargarVista("gestion-eventos.fxml");
    }

    @FXML
    private void handleGestionEventos() {
        cargarVista("gestion-eventos.fxml");
    }

    @FXML
    private void handleGestionRecintos() {
        cargarVista("gestion-resinto.fxml");
    }

    @FXML
    private void handleGestionAsientos() {
        cargarVista("gestion-asientos.fxml");
    }

    @FXML
    private void handleIncidencias() {
        cargarVista("Incidencias.fxml");
    }

    @FXML
    private void handleMetricas() {
        cargarVista("Metricas.fxml");
    }

    @FXML
    private void handleCerrarSesionAdmin() {
        // Lógica de cierre de sesión
        System.out.println("Sesión cerrada.");
    }

    private void cargarVista(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    App.class.getResource("/org/example/pfeventos20261/UI/administradorInterface/"+fxml)
            );
            Parent  rootLayout = loader.load();

            Object controller = loader.getController();
            if (controller instanceof DashBoardInjectable) {
                ((DashBoardInjectable) controller).setDashboardController(this);
            }

            loadView(rootLayout);
        } catch (IOException e) {
            e.printStackTrace();
            mostrarError("No se pudo cargar la vista");
        }
    }


    private void loadView(Parent view) {
        AnchorPane.setTopAnchor(view, 0.0);
        AnchorPane.setBottomAnchor(view, 0.0);
        AnchorPane.setLeftAnchor(view, 0.0);
        AnchorPane.setRightAnchor(view, 0.0);
        contentAreaAdmin.getChildren().clear();
        contentAreaAdmin.getChildren().add(view);

    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Carga de vista fallida");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}