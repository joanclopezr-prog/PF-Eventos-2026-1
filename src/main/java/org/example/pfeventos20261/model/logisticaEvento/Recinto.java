package org.example.pfeventos20261.model.logisticaEvento;

import java.util.List;

public class Recinto {
    private String idRecinto;
    private String nombre;
    private String direccion;
    private List<Zona> zonas;

    public Recinto(String idRecinto, String nombre, String direccion, List<Zona> zonas) {
        this.idRecinto = idRecinto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.zonas = zonas;
    }

    public List<Zona> getZonas() {
        return zonas;
    }
}
