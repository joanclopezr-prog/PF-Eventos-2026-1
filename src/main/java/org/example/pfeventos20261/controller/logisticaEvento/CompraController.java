package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.SimuladorDB;
import org.example.pfeventos20261.model.logisticaVentas.Compra;

import java.util.List;

public class CompraController {
    private SimuladorDB simuladorDB;

    public CompraController(SimuladorDB simuladorDB) {
        this.simuladorDB = simuladorDB;
    }

    public void addCompra(Compra compra){
        simuladorDB.addCompra(compra);
    }

    public void removeCompra(Compra compra){
        simuladorDB.removeCompra(compra);
    }

    public void updateCompra(Compra vieja, Compra nueva){
        simuladorDB.updateCompra(vieja, nueva);
    }
}