package org.example.pfeventos20261.viewController.administrador;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.example.pfeventos20261.App;
import org.example.pfeventos20261.controller.logisticaEvento.RecintoController;
import org.example.pfeventos20261.model.ParMutable;
import org.example.pfeventos20261.model.enums.EstadoAsiento;
import org.example.pfeventos20261.model.enums.TipoZona;
import org.example.pfeventos20261.model.logisticaEvento.Asiento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.Zona;


public class GestionAsientosViewController implements DashBoardInjectable{
    private RecintoController recintoController;
    private DashboardAdminViewController dashboard;


    @FXML private ComboBox<Recinto> cbRecintoDestino;
    @FXML private ComboBox<TipoZona> cbTipoZona;
    @FXML private TextField txtIdZona, txtNombreZona, txtPrecioBase, txtZonaX, txtZonaY;
    @FXML private TextField txtNumeroAsiento, txtAsientoX, txtAsientoY;
    @FXML private Label lblZonaActual;
    @FXML private GridPane gridRecinto;

    private Recinto recintoActual;
    private Zona zonaSeleccionada;
    private Asiento asientoSeleccionado;
    private TipoZona tipoZonaSeleccionada;

    @FXML
    void initialize() {
        recintoController = new RecintoController(App.proxy);
        initView();
        initListeners();
    }

    @FXML
    public void onGuardarZona(ActionEvent event) {
        guardarZona();
    }

    @FXML
    public void onGuardarAsiento(ActionEvent event) {
        guardarAsiento();
    }
    @FXML
    public void onEditarZona(ActionEvent actionEvent) {

    }
    @FXML
    public void onEliminarZona(ActionEvent actionEvent) {
    }
    @FXML
    public void onEditarAsiento(ActionEvent actionEvent) {
    }
    @FXML
    public void onEliminarAsiento(ActionEvent actionEvent) {
    }

    private void initView() {

        cbRecintoDestino.setItems(FXCollections.observableArrayList(App.proxy.getRecintos()));

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
            if (recintoActual != null){
                refrescarGrid();
            }
        });
    }

    private void guardarZona(){
        if (recintoActual == null) return;
        try {
            Zona zona = new Zona(
                    txtIdZona.getText(),
                    Double.parseDouble(txtPrecioBase.getText()),
                    tipoZonaSeleccionada,
                    txtNombreZona.getText(),
                    new ParMutable(
                            Integer.parseInt(txtZonaX.getText()),
                            Integer.parseInt(txtZonaY.getText())
                    )
            );
            recintoController.agregarZona(recintoActual, zona);
            zonaSeleccionada = zona;
            lblZonaActual.setText(zona.getNombre());
        } catch (Exception e) {
            mostrarAlerta("error", "datos de zona invalidos");
            return;
        }
        refrescarGrid();
        limpiarCamposZona();
    }

    private void editarZona(){
        if (recintoActual == null) return;
        try {
            Zona zona = new Zona(
                    txtIdZona.getText(),
                    Double.parseDouble(txtPrecioBase.getText()),
                    tipoZonaSeleccionada,
                    txtNombreZona.getText(),
                    new ParMutable(
                            Integer.parseInt(txtZonaX.getText()),
                            Integer.parseInt(txtZonaY.getText())
                    )
            );
            recintoController.agregarZona(recintoActual, zona);
            zonaSeleccionada = zona;
            lblZonaActual.setText(zona.getNombre());
        } catch (Exception e) {
            mostrarAlerta("error", "datos de zona invalidos");
            return;
        }
        refrescarGrid();
        limpiarCamposZona();
    }


    private void guardarAsiento(){
        if (zonaSeleccionada == null) {
            mostrarAlerta("ojo", "seleccione una zona primero.");
            return;
        }
        try {
            Asiento asiento = new Asiento(
                    "AS-" + txtNumeroAsiento.getText(),
                    Integer.parseInt(txtNumeroAsiento.getText()),
                    new ParMutable(
                            Integer.parseInt(txtAsientoX.getText()),
                            Integer.parseInt(txtAsientoY.getText())
                    ),
                    EstadoAsiento.DISPONIBLE
            );
            recintoController.agregarAsiento(recintoActual, zonaSeleccionada, asiento);
        } catch (Exception e) {
            mostrarAlerta("error", "datos de asiento invalidos.");
            return;
        }
        refrescarGrid();
        limpiarCamposAsiento();
    }

    private void refrescarGrid() {
        gridRecinto.getChildren().clear();
        if (recintoActual == null || recintoActual.getZonas() == null) return;

        for (Zona z : recintoActual.getZonas()) {
            Button btnZona = new Button(z.getNombre());

            btnZona.setOnAction(e -> seleccionarZona(z));
            btnZona.setStyle("-fx-background-color:"+ colorSegunEstadoZona(z.getTipoZona())+";");

            GridPane.setHgrow(btnZona, Priority.SOMETIMES);
            GridPane.setVgrow(btnZona, Priority.SOMETIMES);
            btnZona.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            GridPane gridZona = new GridPane();

            if (z.getAsientos() != null) {
                for (Asiento a : z.getAsientos()) {
                    Button btnAsiento = new Button(String.valueOf(a.getNumero()));
                    btnAsiento.setOnAction(e -> seleccionarAsiento(a,z));
                    btnAsiento.setStyle(
                            "-fx-background-color:"+colorSegunEstadoAsiento(a.getEstado())+";" +
                                    "-fx-background-radius: 50;" +
                                    "-fx-border-radius: 50;"
                    );
                    gridZona.add(btnAsiento,
                            a.getPosicion().getX() - 1,
                            a.getPosicion().getY() - 1);
                }
            }
            VBox contenedorZona = new VBox(btnZona, gridZona);
            contenedorZona.setStyle("-fx-background-color:" + colorSegunEstadoZona(z.getTipoZona()) + ";");

            gridRecinto.add(contenedorZona,
                    z.getPosicion().getX() - 1,
                    z.getPosicion().getY() - 1);
        }
    }


    private void seleccionarZona(Zona z) {
        txtIdZona.setText(z.getIdZona());
        txtNombreZona.setText(z.getNombre());
        txtPrecioBase.setText(z.getPrecioBase()+"");
        txtZonaY.setText(z.getPosicion().getY()+"");
        txtZonaX.setText(z.getPosicion().getX()+"");
        cbTipoZona.setValue(z.getTipoZona());
        zonaSeleccionada = z;
        lblZonaActual.setText(z.getNombre());
    }


    //falta logica aca mi muchacho
    private void seleccionarAsiento(Asiento a, Zona z) {
        txtNumeroAsiento.setText(a.getNumero()+"");
        txtAsientoX.setText(a.getPosicion().getX()+"");
        txtAsientoY.setText(a.getPosicion().getY()+"");
        zonaSeleccionada = z;
        asientoSeleccionado = a;
    }

    private String colorSegunEstadoZona(TipoZona zona) {
        return switch (zona) {
            case VIP -> "#2ecc71";
            case PREFERENCIAL  -> "#e67e22";
            case GENERAL    -> "#e74c3c";
        };
    }
    private String colorSegunEstadoAsiento(EstadoAsiento estado) {
        return switch (estado) {
            case DISPONIBLE -> "#2ecc71";
            case RESERVADO  -> "#e67e22";
            case VENDIDO    -> "#e74c3c";
            case BLOQUEADO  -> "#808080";
        };
    }

    private void limpiarCamposZona() {
        txtIdZona.clear();
        txtNombreZona.clear();
        txtPrecioBase.clear();
        txtZonaY.clear();
        txtZonaX.clear();
    }
    private void limpiarCamposAsiento(){
        txtNumeroAsiento.clear();
        txtAsientoX.clear();
        txtAsientoY.clear();
    }

    private void mostrarAlerta(String titulo, String msj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(msj);
        alert.showAndWait();
    }

    public void setDashboardController(DashboardAdminViewController dashboard) {
        this.dashboard = dashboard;
    }
}