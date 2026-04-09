package org.example.pfeventos20261.viewController.administrador;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import org.example.pfeventos20261.App;
import org.example.pfeventos20261.controller.logisticaEvento.EventoController;
import org.example.pfeventos20261.controller.logisticaEvento.RecintoController;
import org.example.pfeventos20261.model.enums.EstadoAsiento;
import org.example.pfeventos20261.model.enums.TipoZona;
import org.example.pfeventos20261.model.logisticaEvento.Asiento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.Zona;

public class GestionAsientosViewController {

    private EventoController eventoController;
    private RecintoController recintoController;

    @FXML private ComboBox<Recinto> cbRecintoDestino;
    @FXML private ComboBox<TipoZona> cbTipoZona;
    @FXML private TextField txtIdZona, txtNombreZona, txtPrecioBase, txtZonaX, txtZonaY;
    @FXML private TextField txtNumeroAsiento, txtAsientoX, txtAsientoY;
    @FXML private Label lblZonaActual;
    @FXML private Pane paneMapaRecinto;

    private Recinto recintoActual;
    private Zona zonaSeleccionada;
    private TipoZona tipoZonaSeleccionada;

    @FXML
    void initialize() {
        eventoController = new EventoController(App.simuladorDB);
        recintoController = new RecintoController(App.simuladorDB);

        initView();
        initListeners();
    }

    private void initView() {
        cbRecintoDestino.setItems(FXCollections.observableArrayList(App.simuladorDB.getRecintos()));
        cbRecintoDestino.setCellFactory(lv -> new ListCell<Recinto>() {
            @Override
            protected void updateItem(Recinto recinto, boolean empty) {
                super.updateItem(recinto, empty);
                setText(empty || recinto == null ? null : recinto.getNombre() + " (" + recinto.getIdRecinto() + ")");
            }
        });
        cbRecintoDestino.setButtonCell(new ListCell<Recinto>() {
            @Override
            protected void updateItem(Recinto recinto, boolean empty) {
                super.updateItem(recinto, empty);
                setText(empty || recinto == null ? null : recinto.getNombre() + " (" + recinto.getIdRecinto() + ")");
            }
        });

        cbTipoZona.setItems(FXCollections.observableArrayList(TipoZona.values()));

    }

    private void initListeners() {
        cbTipoZona.valueProperty().addListener((obs, oldValue, newValue) -> {
            tipoZonaSeleccionada = newValue;
            System.out.println("Seleccion actual: " + tipoZonaSeleccionada);
        });
        cbRecintoDestino.valueProperty().addListener((obs, oldValue, newValue) -> {
            recintoActual = newValue;
            System.out.println("Seleccion actual: " + recintoActual);
        });

        paneMapaRecinto.setOnMouseClicked(event -> {
            String x = String.valueOf((int) event.getX());
            String y = String.valueOf((int) event.getY());

            txtZonaX.setText(x); txtZonaY.setText(y);
            txtAsientoX.setText(x); txtAsientoY.setText(y);
        });
    }

    @FXML
    public void onGuardarZona(ActionEvent event) {
        if (recintoActual == null) return;

        try {
            Zona nuevaZona = new Zona(
                    txtIdZona.getText(),
                    Double.parseDouble(txtPrecioBase.getText()),
                    tipoZonaSeleccionada,
                    txtNombreZona.getText(),
                    new Pair<>((int) Double.parseDouble(txtZonaX.getText()), (int) Double.parseDouble(txtZonaY.getText()))
            );

            recintoController.agregarZona(recintoActual, nuevaZona);

            this.zonaSeleccionada = nuevaZona;
            lblZonaActual.setText(nuevaZona.getNombre());

            refrescarMapa();
            limpiarCamposZona();
        } catch (Exception e) {
            mostrarAlerta("Error", "Datos de zona inválidos.");
        }
    }

    @FXML
    public void onGuardarAsiento(ActionEvent event) {
        if (zonaSeleccionada == null) {
            mostrarAlerta("Aviso", "Selecciona una zona en el mapa primero.");
            return;
        }

        try {
            Asiento nuevoAsiento = new Asiento(
                    "AS-" + txtNumeroAsiento.getText(),
                    Integer.parseInt(txtNumeroAsiento.getText()),
                    new Pair<>((int) Double.parseDouble(txtAsientoX.getText()), (int) Double.parseDouble(txtAsientoY.getText())),
                    EstadoAsiento.DISPONIBLE
            );

            recintoController.agregarAsiento(recintoActual, zonaSeleccionada.getIdZona(), nuevoAsiento);
            refrescarMapa();

        } catch (Exception e) {
            mostrarAlerta("Error", "Datos de asiento inválidos.");
        }

    }

    private void refrescarMapa() {
        paneMapaRecinto.getChildren().clear();
        if (recintoActual == null) return;

        for (Zona z : recintoActual.getZonas()) {
            pintarZona(z);
            for (Asiento a : z.getAsientos()) {
                pintarAsiento(a);
            }
        }
    }

    private void pintarZona(Zona z) {
        Rectangle rect = new Rectangle(80, 40, Color.web("#3498db", 0.2));
        rect.setStroke(Color.web("#3498db"));
        rect.setLayoutX(z.getPosicion().getKey());
        rect.setLayoutY(z.getPosicion().getValue());

        rect.setOnMouseClicked(e -> {
            this.zonaSeleccionada = z;
            lblZonaActual.setText(z.getNombre());
            e.consume(); // Evita que el clic llegue al Pane de fondo
        });

        paneMapaRecinto.getChildren().add(rect);
    }

    private void pintarAsiento(Asiento a) {
        Circle c = new Circle(6, Color.web("#2ecc71"));
        c.setLayoutX(a.getPosicion().getKey());
        c.setLayoutY(a.getPosicion().getValue());
        paneMapaRecinto.getChildren().add(c);
    }

    private void limpiarCamposZona() {
        txtIdZona.clear(); txtNombreZona.clear(); txtPrecioBase.clear();
    }

    private void mostrarAlerta(String titulo, String msj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(msj);
        alert.showAndWait();
    }
}