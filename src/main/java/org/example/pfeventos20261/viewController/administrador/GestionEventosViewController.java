package org.example.pfeventos20261.viewController.administrador;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.example.pfeventos20261.App;
import org.example.pfeventos20261.model.enums.EstadoEvento;
import org.example.pfeventos20261.model.enums.TipoZona;
import org.example.pfeventos20261.model.logisticaEvento.Evento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;

public class GestionEventosViewController {

    @FXML private Button btnNuevoEvento;
    @FXML private Button btnEditar;
    @FXML private Button btnPublicar;
    @FXML private Button btnPausar;
    @FXML private Button btnCancelar;
    @FXML private Button btnLimpiarFiltros;

    @FXML private TextField txtNombre;
    @FXML private TextField txtCategoria;
    @FXML private ComboBox<EstadoEvento> cbEstado;
    @FXML private ComboBox<Recinto> cbRecinto;
    @FXML private TableView<Evento> tblEventos;
    @FXML private TableColumn<Evento, String> colId;
    @FXML private TableColumn<Evento, String> colNombre;
    @FXML private TableColumn<Evento, String> colFecha;
    @FXML private TableColumn<Evento, String> colLugar;
    @FXML private TableColumn<Evento, String> colCategoria;
    @FXML private TableColumn<Evento, String> colEstado;

    private ObservableList<Evento> listaEventos;
    Evento eventoSeleccionado;

    @FXML
    private void initialize() {
        configurarTabla();
        cargarEventos();
        configurarFiltros();
        listenerSelecionEvento();
    }

    private void configurarTabla() {
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaHora().toString()));
        colLugar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRecinto().toString()));
        colCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString()));
    }

    private void cargarEventos() {
        listaEventos = FXCollections.observableArrayList(App.proxy.getEventos());
        tblEventos.setItems(listaEventos);
    }

    private void configurarFiltros() {
        cbEstado.setItems(FXCollections.observableArrayList(EstadoEvento.values()));

        cbRecinto.setItems(FXCollections.observableArrayList(App.proxy.getRecintos()));
        cbRecinto.setCellFactory(lv -> new ListCell<Recinto>() {
            @Override
            protected void updateItem(Recinto recinto, boolean empty) {
                super.updateItem(recinto, empty);
                setText(empty || recinto == null ? null : recinto.getNombre() + " (" + recinto.getIdRecinto() + ")");
            }
        });
    }

    @FXML
    private void nuevoEvento(ActionEvent e) {

    }

    @FXML
    private void editarEvento(ActionEvent e) {
        if (eventoSeleccionado != null) {
            //falta logica aca
        }
    }


    @FXML
    private void limpiarFiltros(ActionEvent e) {
        txtNombre.clear();
        txtCategoria.clear();
        cbRecinto.getSelectionModel().clearSelection();
        cbEstado.getSelectionModel().clearSelection();
        cargarEventos();
    }

    private void listenerSelecionEvento() {
        tblEventos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            eventoSeleccionado = (Evento) newSelection;
//            selectedPropietario = newSelection;
        });
    }
}
