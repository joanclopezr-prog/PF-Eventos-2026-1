package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.Cache;
import org.example.pfeventos20261.model.logisticaEvento.Asiento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.Zona;

import java.util.ArrayList;
import java.util.List;

public class RecintoController {
    private Cache proxy;

    public RecintoController(Cache proxy) {
        this.proxy = proxy;
    }

    public void addRecinto(Recinto recinto){
        proxy.recintos().add(recinto);
    }

    public void removeRecinto(Recinto recinto){
        proxy.recintos().remove(recinto);
    }

    public void updateRecinto(Recinto viejo, Recinto nuevo){
        proxy.recintos().update(viejo,nuevo);
    }

    public List<Recinto> getRecintos(){
        return proxy.recintos().getAll();
    }

    public void agregarZona(Recinto recinto, Zona zona) {
        proxy.recintos().getFachade(recinto).getzonas().add(zona);
    }
    public void eliminarZona(Recinto recinto, Zona zona) {
        proxy.recintos().getFachade(recinto).getzonas().remove(zona);
    }
    public void editarZona(Recinto recinto, Zona zona,Zona zonaNueva){
        int index = proxy.recintos().getFachade(recinto).getzonas().indexOf(zona);
        if (index != 0){
            proxy.recintos().getFachade(recinto).getzonas().set(index,zonaNueva);
        }
    }
    public List<Asiento> getAsientos(Recinto recinto,Zona zona){
        return proxy.recintos().getFachade(recinto).getAsientos(zona);
    }


    public void agregarAsiento(Recinto recinto, Zona zona, Asiento nuevoAsiento) {
        proxy.recintos().getFachade(recinto).getAsientos(zona).add(nuevoAsiento);
    }
}