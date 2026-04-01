package org.example.pfeventos20261.model.logisticaEvento;

import javafx.util.Pair;
import org.example.pfeventos20261.model.enums.EstadoAsiento;

public class Asiento {
    private String idAsiento;
    private Pair<Integer,Integer> posicion;
    private int numero;
    private EstadoAsiento estado;

    public Asiento(String idAsiento, int numero,Pair<Integer,Integer> posicion, EstadoAsiento estado) {
        this.idAsiento = idAsiento;
        this.numero = numero;
        this.posicion = posicion;
        this.estado = estado;
    }
}
