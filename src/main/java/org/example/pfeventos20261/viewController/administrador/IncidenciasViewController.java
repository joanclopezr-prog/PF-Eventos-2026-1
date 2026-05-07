package org.example.pfeventos20261.viewController.administrador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import org.example.pfeventos20261.App;
import org.example.pfeventos20261.controller.logisticaEvento.RecintoController;
import org.example.pfeventos20261.model.enums.EstadoAsiento;
import org.example.pfeventos20261.model.logisticaEvento.Asiento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.Zona;

import java.util.ArrayList;
import java.util.List;

public class IncidenciasViewController implements DashBoardInjectable{
    private DashboardAdminViewController dashboard;
    private RecintoController recintoController;

    @FXML private PieChart pieChart;
    @FXML private StackedBarChart<String, Number> stackedBarChart;
    @FXML private ComboBox<Recinto> cbRecinto;

    private List<Asiento> asientos;
    private ObservableList<Recinto> recintos;
    private Recinto recintoSeleccionado;

    @FXML
    public void initialize() {
        recintoController = new RecintoController(App.proxy);
        initListeners();
        initView();
    }

    private void initListeners() {
        cbRecinto.valueProperty().addListener((obs, oldValue, newValue) -> {
            recintoSeleccionado = newValue;
            System.out.println("Seleccion actual: " + recintoSeleccionado);
            if (recintoSeleccionado != null){
                asientos = getAsientosRecinto(recintoSeleccionado);
                refrescarGrid();
            }
        });
    }

    private void refrescarGrid() {
        if (asientos != null ) {
            if (!asientos.isEmpty()) {
                pieChart.getData().addAll(
                        new PieChart.Data("Disponible", calculateValueAsiento(asientos,EstadoAsiento.DISPONIBLE)),
                        new PieChart.Data("Reservado",  calculateValueAsiento(asientos,EstadoAsiento.RESERVADO)),
                        new PieChart.Data("Vendido",  calculateValueAsiento(asientos,EstadoAsiento.VENDIDO)),
                        new PieChart.Data("Bloqueado" , calculateValueAsiento(asientos,EstadoAsiento.BLOQUEADO))
                );
            }
        }
        for (Zona z : recintoSeleccionado.getZonas()) {
            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            serie.setName(z.getNombre());
            int disponibles = contarAsientosPorEstado(z.getAsientos(), EstadoAsiento.DISPONIBLE);
            int reservados  = contarAsientosPorEstado(z.getAsientos(), EstadoAsiento.RESERVADO);
            int vendidos    = contarAsientosPorEstado(z.getAsientos(), EstadoAsiento.VENDIDO);
            int bloqueados  = contarAsientosPorEstado(z.getAsientos(), EstadoAsiento.BLOQUEADO);
            serie.getData().add(new XYChart.Data<>("Disponibles", disponibles));
            serie.getData().add(new XYChart.Data<>("Reservados", reservados));
            serie.getData().add(new XYChart.Data<>("Vendidos", vendidos));
            serie.getData().add(new XYChart.Data<>("Bloqueados", bloqueados));

            stackedBarChart.getData().add(serie);
        }

    }
    private int contarAsientosPorEstado(List<Asiento> asientos, EstadoAsiento estado) {
        if (asientos == null) return 0;
        int count = 0;
        for (Asiento a : asientos) {
            if (a.getEstado() == estado) {
                count++;
            }
        }
        return count;
    }


    private void initView() {
        cbRecinto.setItems(FXCollections.observableArrayList(App.proxy.recintos().getAll()));
        cbRecinto.setCellFactory(lv -> new ListCell<Recinto>() {
            @Override
            protected void updateItem(Recinto recinto, boolean empty) {
                super.updateItem(recinto, empty);
                setText(empty || recinto == null ? null : recinto.getNombre() + " (" + recinto.getIdRecinto() + ")");
            }
        });
    }

    private int calculateValueAsiento(List<Asiento> asientos, EstadoAsiento estadoAsiento){
        int count = 0;
        for (Asiento a: asientos){
            if (a.getEstado() == estadoAsiento){
                count ++;
            }
        }
        return count;
    }
    private List<Asiento> getAsientosRecinto(Recinto recinto){
        List<Asiento> asietosResinto = new ArrayList<>();
        for (Zona z : recinto.getZonas()){
            asietosResinto.addAll(z.getAsientos());
        }
        return asietosResinto;
    }


    @Override
    public void setDashboardController(DashboardAdminViewController dashboardController) {
        this.dashboard = dashboard;
    }
}