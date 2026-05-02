package org.example.pfeventos20261.model.logisticaEvento;

import org.example.pfeventos20261.model.enums.EstadoEvento;

import java.time.LocalDate;
import java.util.List;

public class Evento implements IEvento{
    private String id;
    private String nombre;
    private String categoria;
    private LocalDate fechaHora;
    private EstadoEvento estado;
    private List<IEvento> recintos;

    public Evento(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.categoria = builder.categoria;
        this.fechaHora = builder.fechaHora;
        this.estado = builder.estado;
        this.recintos = builder.recintos;
    }

    @Override
    public List<IEvento> getHijos() {
        return recintos;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public EstadoEvento getEstado() {
        return estado;
    }

    public List<IEvento> getRecinto() {
        return recintos;
    }


    public static class Builder {
        private String id;
        private String nombre;
        private String categoria;
        private LocalDate fechaHora;
        private EstadoEvento estado;
        private List<IEvento> recintos;

        public Builder(String id) {
            this.id = id;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder categoria(String categoria) {
            this.categoria = categoria;
            return this;
        }


        public Builder fechaHora(LocalDate fechaHora) {
            this.fechaHora = fechaHora;
            return this;
        }

        public Builder estado(EstadoEvento estado) {
            this.estado = estado;
            return this;
        }

        public Builder recintos(List<IEvento> recintos) {
            this.recintos = recintos;
            return this;
        }

        public Evento build() {
            return new Evento(this);
        }
    }
}
