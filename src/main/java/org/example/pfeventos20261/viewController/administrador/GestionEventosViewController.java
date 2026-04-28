package org.example.pfeventos20261.viewController.administrador;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.example.pfeventos20261.App;
import org.example.pfeventos20261.controller.logisticaEvento.EventoController;
import org.example.pfeventos20261.model.enums.EstadoEvento;
import org.example.pfeventos20261.model.logisticaEvento.Evento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;

public class GestionEventosViewController implements DashBoardInjectable{
    EventoController eventoController;
    private DashboardAdminViewController dashboard;
    @FXML private Button btnNuevoEvento;
    @FXML private Button btnEditar;
    @FXML private Button btnPublicar;
    @FXML private Button btnPausar;
    @FXML private Button btnCancelar;
    @FXML private Button btnLimpiarFiltros;

    @FXML private TextField txtNombre;
    @FXML private TextField txtCategoria;
    @FXML private DatePicker dpkFecha;
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
        eventoController = new EventoController(App.proxy);
        cargarEventos();
        initView();
        listenerSelecionEvento();
    }

    private void initView() {
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaHora().toString()));
        colLugar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRecinto().toString()));
        colCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString()));
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

    private void cargarEventos() {
        listaEventos = FXCollections.observableArrayList(eventoController.getEventos());
        tblEventos.setItems(listaEventos);
    }

    @FXML
    public void onLimpiar(ActionEvent e){
        limpiarCampos();
    }
    @FXML
    public void onEliminar(ActionEvent e){
        eliminarEvento();
    }
    @FXML
    public void onAdd(ActionEvent e){
        addEvento();
    }
    @FXML
    public void onEditar(ActionEvent e){
        editarEvento();
    }

    private void addEvento() {
        try {
            Evento evento = new Evento(new Evento.Builder(txtNombre.toString())
                    .categoria(txtCategoria.toString())
                    .estado(cbEstado.getValue())
                    .recinto(cbRecinto.getValue())
                    .fechaHora(dpkFecha.getValue())
            );
            eventoController.addEvento(evento);
            eventoSeleccionado = evento;
            limpiarCampos();
        } catch (Exception i) {
            mostrarAlerta("error", "datos de evento son invalidos."+ "-- "+i+" --");
        }

    }

    private void editarEvento() {
        if (eventoSeleccionado != null) {
            Evento evento = new Evento(new Evento.Builder(txtNombre.toString())
                    .categoria(txtCategoria.toString())
                    .estado(cbEstado.getValue())
                    .recinto(cbRecinto.getValue())
                    .fechaHora(dpkFecha.getValue())
            );
            eventoController.updateEvento(eventoSeleccionado,evento);
            eventoSeleccionado = evento;
            limpiarCampos();
        }
    }
    private void eliminarEvento() {
        eventoController.removeEvento(eventoSeleccionado);
        limpiarCampos();
    }



    private void limpiarCampos() {
        txtNombre.clear();
        txtCategoria.clear();
        cbRecinto.getSelectionModel().clearSelection();
        cbEstado.getSelectionModel().clearSelection();
        cargarEventos();
    }

    private void listenerSelecionEvento() {
        tblEventos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            eventoSeleccionado = (Evento) newSelection;
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
