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
import org.example.pfeventos20261.model.ParMutable;
import org.example.pfeventos20261.model.enums.EstadoAsiento;
import org.example.pfeventos20261.model.enums.TipoZona;
import org.example.pfeventos20261.model.logisticaEvento.Asiento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.Zona;

import java.util.List;

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

            txtZonaX.setText(x);
            txtZonaY.setText(y);

            if (zonaSeleccionada != null) {
                int rx = (int) event.getX() - zonaSeleccionada.getPosicion().getX();
                int ry = (int) event.getY() - zonaSeleccionada.getPosicion().getY();
                txtAsientoX.setText(String.valueOf(Math.max(0, rx)));
                txtAsientoY.setText(String.valueOf(Math.max(0, ry)));
            } else {
                txtAsientoX.setText(x);
                txtAsientoY.setText(y);
            }
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
                    new ParMutable(Integer.parseInt(txtZonaX.getText()), Integer.parseInt(txtZonaY.getText()))
            );

            recintoController.agregarZona(recintoActual, nuevaZona);
            System.out.println("resinto"+recintoController.getRecintos().getFirst().getZonas());

            this.zonaSeleccionada = nuevaZona;
            lblZonaActual.setText(nuevaZona.getNombre());

        } catch (Exception e) {
            mostrarAlerta("Error", "Datos de zona inválidos.");
        }
        refrescarMapa();
        limpiarCamposZona();
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
                    new ParMutable(Integer.parseInt(txtAsientoX.getText()),Integer.parseInt(txtAsientoY.getText())),
                    EstadoAsiento.DISPONIBLE
            );

            recintoController.agregarAsiento(recintoActual, zonaSeleccionada, nuevoAsiento);
            refrescarMapa();

        } catch (Exception e) {
            mostrarAlerta("Error", "Datos de asiento inválidos.");
        }

    }

    private void refrescarMapa() {
        paneMapaRecinto.getChildren().clear();
        if (recintoActual == null || recintoActual.getZonas() == null) return;

        for (Zona z : recintoActual.getZonas()) {
            pintarZona(z);
            if (z.getAsientos() == null) continue; // ← continue, no return
            for (Asiento a : z.getAsientos()) {
                pintarAsiento(a, z); // ← pasamos la zona para calcular offset
            }
        }
    }

    private void pintarZona(Zona z) {
        // Tamaño fijo por tipo de zona, o un mínimo razonable
        double ancho = calcularAnchoZona(z);
        double alto  = calcularAltoZona(z);

        Rectangle rect = new Rectangle(ancho, alto, Color.web("#3498db", 0.2));
        rect.setStroke(Color.web("#3498db"));
        rect.setStrokeWidth(2);

        // Posición de la zona en el panel
        rect.setLayoutX(z.getPosicion().getX());
        rect.setLayoutY(z.getPosicion().getY());

        // Label con el nombre dentro de la zona
        Label lbl = new Label(z.getNombre());
        lbl.setLayoutX(z.getPosicion().getX() + 4);
        lbl.setLayoutY(z.getPosicion().getY() + 4);
        lbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #2980b9;");

        rect.setOnMouseClicked(e -> {
            this.zonaSeleccionada = z;
            lblZonaActual.setText(z.getNombre());
            e.consume();
        });

        paneMapaRecinto.getChildren().addAll(rect, lbl);
    }

    private void pintarAsiento(Asiento a, Zona zona) {
        Circle c = new Circle(6, Color.web("#2ecc71"));

        // Posición absoluta = posición zona + posición relativa del asiento
        c.setLayoutX(zona.getPosicion().getX()*12 + a.getPosicion().getX()*12);
        c.setLayoutY(zona.getPosicion().getY()*12 + a.getPosicion().getY()*12);

        c.setStroke(Color.web("#27ae60"));
        c.setStrokeWidth(1);

        // Tooltip con número de asiento
        Tooltip tp = new Tooltip("Asiento " + a.getNumero());
        Tooltip.install(c, tp);

        paneMapaRecinto.getChildren().add(c);
    }

    private double calcularAnchoZona(Zona z) {
        if (z.getAsientos() == null || z.getAsientos().isEmpty()) return 80;
        return z.getAsientos().stream()
                .mapToInt(a -> a.getPosicion().getX())
                .max()
                .orElse(60) + 20;
    }

    private double calcularAltoZona(Zona z) {
        if (z.getAsientos() == null || z.getAsientos().isEmpty()) return 80;
        return z.getAsientos().stream()
                .mapToInt(a -> a.getPosicion().getY())
                .max()
                .orElse(60) + 20;
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