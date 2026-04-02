package org.example.pfeventos20261.viewController.administrador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import org.example.pfeventos20261.model.SimuladorDB;
import org.example.pfeventos20261.model.enums.EstadoAsiento;
import org.example.pfeventos20261.model.enums.TipoZona;
import org.example.pfeventos20261.model.logisticaEvento.Asiento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.Zona;

import java.util.ArrayList;

public class GestionAsientosViewController {

    @FXML private Button btnGuardarZona;
    @FXML private Button btnGuardarAsiento;
    @FXML private TabPane tabPaneEditor;
    @FXML private ComboBox<Recinto> cbRecintoDestino;
    @FXML private TextField txtIdZona, txtNombreZona, txtPrecioBase, txtZonaX, txtZonaY;
    @FXML private TextField txtNumeroAsiento, txtAsientoX, txtAsientoY;
    @FXML private Label lblZonaActual;
    @FXML private Pane paneMapaRecinto;

    private Recinto recintoActual;
    private Zona zonaSeleccionada;

    @FXML
    public void initialize() {
        cbRecintoDestino.getItems().addAll(SimuladorDB.recintos);
        cbRecintoDestino.setOnAction(e -> {
            this.recintoActual = cbRecintoDestino.getValue();
            refrescarMapa();
        });
        paneMapaRecinto.setOnMouseClicked(event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            txtZonaX.setText(String.valueOf(x));
            txtZonaY.setText(String.valueOf(y));
            txtAsientoX.setText(String.valueOf(x));
            txtAsientoY.setText(String.valueOf(y));
        });
    }

    @FXML
    private void handleGuardarZona() {
        if (recintoActual == null) return;

        String id = txtIdZona.getText();
        String nombre = txtNombreZona.getText();
        double precio = Double.parseDouble(txtPrecioBase.getText());
        int x = Integer.parseInt(txtZonaX.getText());
        int y = Integer.parseInt(txtZonaY.getText());
        Zona nuevaZona = new Zona(id, precio, TipoZona.GENERAL, nombre, new Pair<>(x, y), new ArrayList<>());
        recintoActual.getZonas().add(nuevaZona);
        this.zonaSeleccionada = nuevaZona;
        lblZonaActual.setText(nombre);

        refrescarMapa();
    }

    @FXML
    private void handleGuardarAsiento() {
        if (zonaSeleccionada == null) {
            System.out.println("Primero debes crear o seleccionar una zona");
            return;
        }

        int num = Integer.parseInt(txtNumeroAsiento.getText());
        int x = Integer.parseInt(txtAsientoX.getText());
        int y = Integer.parseInt(txtAsientoY.getText());

        // Creamos el asiento
        Asiento nuevoAsiento = new Asiento("A-" + num, num, new Pair<>(x, y), EstadoAsiento.DISPONIBLE);

        zonaSeleccionada.getAsientos().add(nuevoAsiento);

        refrescarMapa();
    }

    /**
     * El corazón de la vista: Borra el panel y dibuja todo según el modelo
     */
    private void refrescarMapa() {
        paneMapaRecinto.getChildren().clear();

        if (recintoActual == null) return;

        for (Zona z : recintoActual.getZonas()) {
            Rectangle rect = new Rectangle(100, 50);
            rect.setLayoutX(z.getPosicion().getKey());
            rect.setLayoutY(z.getPosicion().getValue());
            rect.setFill(Color.web("#3498db", 0.3)); // azul transparente
            rect.setStroke(Color.web("#3498db"));
            rect.setOnMouseClicked(e -> {
                this.zonaSeleccionada = z;
                lblZonaActual.setText(z.getNombre());
                e.consume();
            });
            paneMapaRecinto.getChildren().add(rect);
            for (Asiento a : z.getAsientos()) {
                Circle circulo = new Circle(8, Color.web("#2ecc71"));
                circulo.setLayoutX(a.getPosicion().getKey());
                circulo.setLayoutY(a.getPosicion().getValue());

                paneMapaRecinto.getChildren().add(circulo);
            }
        }
    }
}