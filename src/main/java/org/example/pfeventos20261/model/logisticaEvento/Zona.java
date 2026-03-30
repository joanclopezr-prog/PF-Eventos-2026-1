package org.example.pfeventos20261.model.logisticaEvento;

import java.util.List;

public class Zona {
    private String idZona;
    private String nombre;
    private double precioBase;
    private int capacidad;
    private List<Asiento> asientos;

    public Zona(String idZona, String nombre, double precioBase, int capacidad, List<Asiento> asientos) {
        this.idZona = idZona;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.capacidad = capacidad;
        this.asientos = asientos;
    }

}
