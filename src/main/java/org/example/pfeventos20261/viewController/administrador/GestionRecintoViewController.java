package org.example.pfeventos20261.viewController.administrador;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.example.pfeventos20261.App;
import org.example.pfeventos20261.controller.logisticaEvento.RecintoController;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.Zona;

import java.util.stream.Collectors;

public class GestionRecintoViewController implements DashBoardInjectable{
    private RecintoController recintoController;
    private DashboardAdminViewController dashboard;

    @FXML private TextField txtNombreRecinto;
    @FXML private TextField txtDireccionRecinto;

    @FXML private Button btnGuardarRecinto;
    @FXML private Button btnEditarRecinto;
    @FXML private Button btnLimpiarForm;
    @FXML private Button btnEliminarRecinto;
    @FXML private Button btnConfigurarMapa;

    @FXML private TableView<Recinto> tblRecintos;
    @FXML private TableColumn<Recinto, String> colId;
    @FXML private TableColumn<Recinto, String> colNombre;
    @FXML private TableColumn<Recinto, String> colDireccion;
    @FXML private TableColumn<Recinto, String> colZonas;

    private ObservableList<Recinto> listaRecintos;
    private Recinto recintoSeleccionado;

    @FXML
    private void initialize() {
        recintoController = new RecintoController(App.proxy);
        cargarRecintos();
        initView();
        listenerSelecionRecinto();
    }

    @FXML
    public void onLimpiar(ActionEvent e){
        limpiarFormulario();
        cargarRecintos();
    }
    @FXML
    public void onEliminar(ActionEvent e){
        eliminarRecinto();
        cargarRecintos();
    }
    @FXML
    public void onAdd(ActionEvent e){
        guardarRecinto();
        limpiarFormulario();
        cargarRecintos();
    }
    @FXML
    public void onEditar(ActionEvent e){
        editarRecinto();
        limpiarFormulario();
        cargarRecintos();
    }

    private void initView() {
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdRecinto()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
        colZonas.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue()
                                .getZonas()
                                .stream()
                                .map(Zona::getNombre)
                                .collect(Collectors.joining(", "))
                )
        );
    }

    private void cargarRecintos() {
        listaRecintos = FXCollections.observableArrayList(recintoController.getRecintos());
        tblRecintos.setItems(listaRecintos);
    }

    private void guardarRecinto() {
        try{
            Recinto recinto = new Recinto(
                    txtNombreRecinto.getText(),
                    txtNombreRecinto.getText(),
                    txtDireccionRecinto.getText()
            );
            recintoController.addRecinto(recinto);
        } catch (Exception i) {
            mostrarAlerta("error", "datos de evento son invalidos."+ "-- "+i+" --");
        }
    }

    @FXML
    private void editarRecinto() {
        Recinto seleccionado = tblRecintos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            txtNombreRecinto.setText(seleccionado.getNombre());
            txtDireccionRecinto.setText(seleccionado.getDireccion());

        }
    }


    private void eliminarRecinto() {
        if (recintoSeleccionado != null) {
            recintoController.removeRecinto(recintoSeleccionado);
        }
    }

//    private void configurarMapa() {
//
//    }

    private void limpiarFormulario() {
        txtNombreRecinto.clear();
        txtDireccionRecinto.clear();

    }
    private void listenerSelecionRecinto() {
        tblRecintos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            recintoSeleccionado = (Recinto) newSelection;
        });
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
