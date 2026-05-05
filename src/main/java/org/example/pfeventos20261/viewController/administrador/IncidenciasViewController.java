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
        cargarAsientos();
        initView();
        initListeners();
    }

    private void initListeners() {
        cbRecinto.valueProperty().addListener((obs, oldValue, newValue) -> {
            recintoSeleccionado = newValue;
            System.out.println("Seleccion actual: " + recintoSeleccionado);
            if (recintoSeleccionado != null){
                refrescarGrid();
            }
        });
    }
    private void cargarAsientos() {
        if (recintoSeleccionado == null) return ;
        recintos = FXCollections.observableArrayList(recintoController.getRecintos());
    }

    private void refrescarGrid() {

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
        XYChart.Series<String, Number> serie1 = new XYChart.Series<>();
        serie1.setName("Evento 1");
        serie1.getData().add(new XYChart.Data<>("zona vip", 50));
        serie1.getData().add(new XYChart.Data<>("zona preferencial", 80));
        serie1.getData().add(new XYChart.Data<>("zona general", 30));

        XYChart.Series<String, Number> serie2 = new XYChart.Series<>();
        serie2.setName("Evento 2");
        serie2.getData().add(new XYChart.Data<>("Enero", 40));
        serie2.getData().add(new XYChart.Data<>("Febrero", 60));
        serie2.getData().add(new XYChart.Data<>("Marzo", 90));

        stackedBarChart.getData().addAll(serie1, serie2);
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

    @Override
    public void setDashboardController(DashboardAdminViewController dashboardController) {
        this.dashboard = dashboard;
    }
}