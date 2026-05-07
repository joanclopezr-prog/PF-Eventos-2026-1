package org.example.pfeventos20261.viewController.loggin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.pfeventos20261.App;

import java.io.IOException;

public class crearCuentaViewController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;
    @FXML private TextField txtAdminCode;
    @FXML private Button btnCrearCuenta;

    // Código de admin definido en tu lógica
    private static final String ADMIN_CODE = "ADMIN2026";

    @FXML
    private void initialize() {
        btnCrearCuenta.setOnAction(event -> crearCuenta());
    }

    private void crearCuenta() {
        String correo = txtCorreo.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        String adminCode = txtAdminCode.getText();

        // Validaciones básicas
        if (correo == null || correo.isEmpty()) {
            mostrarAlerta("Debe ingresar un correo electrónico.");
            return;
        }
        if (password == null || password.isEmpty()) {
            mostrarAlerta("Debe ingresar una contraseña.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            mostrarAlerta("Las contraseñas no coinciden.");
            return;
        }

        boolean esAdmin = adminCode != null && adminCode.equals(ADMIN_CODE);

        // Aquí puedes llamar a tu servicio/proxy para crear la cuenta
        if (esAdmin) {
            mostrarAlerta("Cuenta de ADMIN creada correctamente.");
        } else {
            mostrarAlerta("Cuenta de usuario creada correctamente.");
        }
    }
    private void volverAlLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    App.class.getResource("UI/login/loggin-view.fxml")
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
