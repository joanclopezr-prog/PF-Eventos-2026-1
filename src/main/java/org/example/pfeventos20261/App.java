package org.example.pfeventos20261;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import org.example.pfeventos20261.model.Cache;
import org.example.pfeventos20261.viewController.administrador.DashboardAdminViewController;
import org.example.pfeventos20261.viewController.loggin.crearCuentaViewController;
import org.example.pfeventos20261.viewController.loggin.logginViewController;

public class App extends Application {
    public static Cache proxy = Cache.getInstance();
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.stage.setTitle("vista principal");
        openViewPrimaria();
        openViewLoggin();
    }

    private void openViewPrimaria() {
        try {
            System.out.println("URL del FXML: " + App.class.getResource("UI/administradorInterface/gestion-asientos.fxml"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UI/administradorInterface/dashboard-admin.fxml"));
//            Parent rootLayout = loader.load();
            BorderPane rootLayout = loader.load();
            DashboardAdminViewController controller = loader.getController();
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void openViewLoggin() {
        try {
            System.out.println("URL del FXML: " + App.class.getResource("UI/login/login.fxml"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UI/login/login.fxml"));
//            Parent rootLayout = loader.load();
            Parent rootLayout = loader.load();
            logginViewController controller = loader.getController();
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

