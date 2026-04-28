package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.SimuladorDB;
import org.example.pfeventos20261.model.logisticaEvento.Asiento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.Zona;

import java.util.List;

public class RecintoController {
    private SimuladorDB simuladorDB;

    public RecintoController(SimuladorDB simuladorDB) {
        this.simuladorDB = simuladorDB;
    }

    public void addRecinto(Recinto recinto){
        simuladorDB.addRecinto(recinto);
    }

    public void removeRecinto(Recinto recinto){
        simuladorDB.removeRecinto(recinto);
    }

    public void updateRecinto(Recinto viejo, Recinto nuevo){
        simuladorDB.updateRecinto(viejo, nuevo);
    }


    public List<Recinto> getRecintos(){
        return simuladorDB.getRecintos();
    }

    public void agregarZona(Recinto recinto, Zona nuevaZona) {
        int index = simuladorDB.getRecintos().indexOf(recinto);
        if (index == -1) return;
        Recinto recintoDB = simuladorDB.getRecintos().get(index);
        recintoDB.getZonas().add(nuevaZona);
        simuladorDB.updateRecinto(recinto, recintoDB);
        System.out.println(".........");

    }


    public void agregarAsiento(Recinto recinto, Zona zona, Asiento nuevoAsiento) {
        int indexRecinto = simuladorDB.getRecintos().indexOf(recinto);
        if (indexRecinto == -1) return;

        Recinto recintoDB = simuladorDB.getRecintos().get(indexRecinto);
        int indexZona = recintoDB.getZonas().indexOf(zona);
        if (indexZona == -1) return;

        recintoDB.getZonas().get(indexZona).getAsientos().add(nuevoAsiento);
        simuladorDB.updateRecinto(recinto, recintoDB);
    }
}