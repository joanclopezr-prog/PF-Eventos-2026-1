package org.example.pfeventos20261.viewController.loggin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.pfeventos20261.App;
import org.example.pfeventos20261.controller.logisticaEvento.UsuarioController;
import org.example.pfeventos20261.model.usuario.Administrador;
import org.example.pfeventos20261.model.usuario.Usuario;

import java.io.IOException;

public class crearCuentaViewController {
    UsuarioController usuarioController;

    @FXML private PasswordField txtTelefono;
    @FXML private PasswordField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtAdminCode;
    @FXML private Button btnCrearCuenta;

    // Código de admin quemado
    private static final String ADMIN_CODE = "ADMIN2026";

    @FXML
    private void initialize() {
        usuarioController = new UsuarioController(App.proxy);
        btnCrearCuenta.setOnAction(event -> crearCuenta());
    }

    private void crearCuenta() {
        String telefono = txtTelefono.getText();
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String password = txtPassword.getText();
        String adminCode = txtAdminCode.getText();

        if (nombre == null || nombre.isEmpty()) {
            mostrarAlerta("Las contraseñas no coinciden.");
            return;
        }
        if (correo == null || correo.isEmpty()) {
            mostrarAlerta("Debe ingresar un correo electrónico.");
            return;
        }
        if (password == null || password.isEmpty()) {
            mostrarAlerta("Debe ingresar una contraseña.");
            return;
        }
        boolean esAdmin = adminCode != null && adminCode.equals(ADMIN_CODE);

        if (esAdmin) {
            mostrarAlerta("Cuenta de ADMIN creada correctamente.");
            Administrador administrador = new Administrador("",nombre,correo,password);
            usuarioController.setPersona(administrador);
            volverAlLogin();
        } else {
            Usuario usuario = new Usuario("",nombre,correo,password,telefono);
            usuarioController.setPersona(usuario);
            mostrarAlerta("Cuenta de usuario creada correctamente.");
            volverAlLogin();
        }
    }
    private void volverAlLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    App.class.getResource("UI/login/login.fxml")
            );
            Parent rootLayout = loader.load();
            Stage stage = (Stage) txtCorreo.getScene().getWindow();
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("No se pudo cargar la vista de login.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro de Cuenta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
