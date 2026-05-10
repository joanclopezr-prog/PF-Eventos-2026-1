package org.example.pfeventos20261.viewController.loggin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.pfeventos20261.App;
import org.example.pfeventos20261.controller.logisticaEvento.UsuarioController;

import java.io.IOException;

public class logginViewController {
    UsuarioController usuarioController;
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink linkRegistro;

    @FXML
    private void initialize() {
        usuarioController = new UsuarioController(App.proxy);
        btnLogin.setOnAction(event -> iniciarSesion());
        linkRegistro.setOnAction(event -> irARegistro());
    }

    private void iniciarSesion() {
        String correo = txtCorreo.getText();
        String password = txtPassword.getText();

        if (correo == null || correo.isEmpty()) {
            mostrarAlerta("Debe ingresar un correo electrónico.");
            return;
        }
        if (password == null || password.isEmpty()) {
            mostrarAlerta("Debe ingresar una contraseña.");
            return;
        }

//        if (correo.equals(usuarioController.getUsuario().getNombre()) && password.equals(usuarioController.getUsuario().getContrasena())) {
//
//            mostrarAlerta("Inicio de sesión exitoso como"+ usuarioController.getUsuario().getNombre());
//        } else {
//            mostrarAlerta("Inicio de sesión exitoso.");
//        }
    }

    private void irARegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    App.class.getResource("UI/login/registrar-view.fxml")
            );
            Parent rootLayout = loader.load();
            Stage stage = (Stage) txtCorreo.getScene().getWindow();
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("No se pudo cargar la vista de registro.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}