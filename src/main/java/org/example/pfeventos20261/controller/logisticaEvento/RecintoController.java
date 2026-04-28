package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.Proxy;
import org.example.pfeventos20261.model.logisticaEvento.Asiento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.Zona;
import org.example.pfeventos20261.viewController.administrador.DashboardAdminViewController;

import java.util.List;

public class RecintoController {
    private Proxy proxy;

    public RecintoController(Proxy proxy) {
        this.proxy = proxy;
    }

    public void addRecinto(Recinto recinto){
        proxy.addRecinto(recinto);
    }

    public void removeRecinto(Recinto recinto){
        proxy.removeRecinto(recinto);
    }

    public void updateRecinto(Recinto viejo, Recinto nuevo){
        proxy.updateRecinto(viejo, nuevo);
    }


    public List<Recinto> getRecintos(){
        return proxy.getRecintos();
    }

    public void agregarZona(Recinto recinto, Zona nuevaZona) {
        int index = proxy.getRecintos().indexOf(recinto);
        if (index == -1) return;
        Recinto recintoDB = proxy.getRecintos().get(index);
        recintoDB.getZonas().add(nuevaZona);
        proxy.updateRecinto(recinto, recintoDB);
        System.out.println(".........");

    }


    public void agregarAsiento(Recinto recinto, Zona zona, Asiento nuevoAsiento) {
        int indexRecinto = proxy.getRecintos().indexOf(recinto);
        if (indexRecinto == -1) return;

        Recinto recintoDB = proxy.getRecintos().get(indexRecinto);
        int indexZona = recintoDB.getZonas().indexOf(zona);
        if (indexZona == -1) return;

        recintoDB.getZonas().get(indexZona).getAsientos().add(nuevoAsiento);
        proxy.updateRecinto(recinto, recintoDB);
    }
}