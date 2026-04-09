package org.example.pfeventos20261;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Parent;
import org.example.pfeventos20261.model.SimuladorDB;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.viewController.administrador.GestionAsientosViewController;

public class App extends Application {
    public static SimuladorDB simuladorDB = SimuladorDB.getInstance();
    private Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.stage.setTitle("Gestion de asientos");
        openViewPrincipal();
    }


    private void openViewPrincipal() {
        try {
            //System.out.println("URL del FXML: " + App.class.getResource("UI/administradorInterface/gestion-asientos.fxml"));
            simuladorDB.addRecinto(new Recinto("10203","lalo","jfaklj"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UI/administradorInterface/gestion-asientos.fxml"));
            Parent rootLayout = loader.load();
            GestionAsientosViewController controller = loader.getController();
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

