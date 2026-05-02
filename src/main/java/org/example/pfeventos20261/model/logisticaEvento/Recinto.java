package org.example.pfeventos20261.model.logisticaEvento;

import java.util.ArrayList;
import java.util.List;

public class Recinto implements IEvento{
    private String idRecinto;
    private String nombre;
    private String direccion;
    private List<IEvento> zonas;

    public Recinto(String idRecinto, String nombre, String direccion) {
        this.idRecinto = idRecinto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.zonas = new ArrayList<>();
    }

    public List<IEvento> getZonas() {
        return zonas;
    }

    public void setZonas(List<IEvento> zonas) {
        this.zonas = zonas;
    }

    public String getIdRecinto() {
        return idRecinto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public List<IEvento> getHijos() {
        return zonas;
    }
}
