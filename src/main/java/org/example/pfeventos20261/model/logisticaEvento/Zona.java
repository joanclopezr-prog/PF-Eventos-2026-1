package org.example.pfeventos20261.model.logisticaEvento;

import javafx.util.Pair;
import org.example.pfeventos20261.model.enums.TipoZona;
import java.util.List;

public class Zona {
    private String idZona;
    private TipoZona tipoZona;
    private String nombre;
    private double precioBase;
    private Pair<Integer,Integer> posicion;
    private List<Asiento> asientos;

    public Zona(String idZona, double precioBase,TipoZona tipoZona,String nombre,Pair<Integer,Integer> posicion, List<Asiento> asientos) {
        this.idZona = idZona;
        this.nombre = nombre;
        this.tipoZona = tipoZona;
        this.precioBase = precioBase;
        this.posicion = posicion;
        this.asientos = asientos;
    }

    public String getNombre() {
        return nombre;
    }

    public Pair<Integer, Integer> getPosicion() {
        return posicion;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }
}
