package org.example.pfeventos20261.model.logisticaEvento;

import org.example.pfeventos20261.model.ParMutable;
import org.example.pfeventos20261.model.enums.EstadoAsiento;

import java.util.Collections;
import java.util.List;

public class Asiento implements IEvento{
    private String idAsiento;
    private ParMutable posicion;
    private int numero;
    private EstadoAsiento estado;

    public Asiento(String idAsiento, int numero, ParMutable posicion, EstadoAsiento estado) {
        this.idAsiento = idAsiento;
        this.numero = numero;
        this.posicion = posicion;
        this.estado = estado;
    }

    public ParMutable getPosicion() {
        return posicion;
    }

    public String getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(String idAsiento) {
        this.idAsiento = idAsiento;
    }

    public void setPosicion(ParMutable posicion) {
        this.posicion = posicion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }

    @Override
    public List<IEvento> getHijos() {
        return Collections.emptyList();
    }
}
