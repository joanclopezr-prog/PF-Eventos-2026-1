package org.example.pfeventos20261.viewController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.example.pfeventos20261.App;
import org.example.pfeventos20261.model.usuario.Administrador;
import org.example.pfeventos20261.model.usuario.Persona;
import org.example.pfeventos20261.model.usuario.Usuario;
import org.example.pfeventos20261.viewController.administrador.DashboardAdminViewController;

import java.io.IOException;

public class ViewFactory {


    public static Parent getView(Persona persona) {

        if (persona instanceof Administrador) {
            try {
                System.out.println("URL del FXML: " + App.class.getResource("UI/administradorInterface/gestion-asientos.fxml"));
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("UI/administradorInterface/dashboard-admin.fxml"));
                Parent rootLayout = loader.load();
                DashboardAdminViewController controller = loader.getController();
                Scene scene = new Scene(rootLayout);
                App.stage.setScene(scene);
                App.stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (persona instanceof Usuario) {
//            return FXMLLoader.load(getClass().getResource("UsuarioView.fxml"));
        }
        return null;
    }
}