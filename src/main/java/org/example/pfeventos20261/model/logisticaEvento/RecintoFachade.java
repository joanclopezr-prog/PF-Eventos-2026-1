package org.example.pfeventos20261.model.logisticaEvento;

import java.util.Collections;
import java.util.List;

public class RecintoFachade {
    Recinto recinto;

    public RecintoFachade(Recinto recinto) {
        this.recinto = recinto;
    }

    public Recinto getRecinto() {
        return recinto;
    }

    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }

    public List<Zona> getzonas(){
        return recinto.getZonas();
    }

    public void setZonas(List<Zona> zonas){
        recinto.setZonas(zonas);
    }

    public List<Asiento> getAsientos(Zona zona){
        int indexZona = recinto.getZonas().indexOf(zona);
        if (indexZona == -1) return Collections.emptyList();
        return recinto.getZonas().get(indexZona).getAsientos();
    }

    public void setAsientos(Zona zona, List<Asiento> asientos){
        int indexZona = recinto.getZonas().indexOf(zona);
        recinto.getZonas().get(indexZona).setAsientos(asientos);
    }
}
