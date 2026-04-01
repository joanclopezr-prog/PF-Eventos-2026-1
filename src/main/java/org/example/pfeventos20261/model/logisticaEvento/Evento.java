package org.example.pfeventos20261.model.logisticaEvento;

import org.example.pfeventos20261.model.enums.EstadoEvento;
import java.time.LocalDateTime;

public class Evento {
    private String id;
    private String nombre;
    private String categoria;
    private LocalDateTime fechaHora;
    private EstadoEvento estado;
    private Recinto recinto;

    private Evento(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.categoria = builder.categoria;
        this.fechaHora = builder.fechaHora;
        this.estado = builder.estado;
        this.recinto = builder.recinto;
    }


    public static class Builder {
        private String id;
        private final String nombre;
        private String categoria;
        private LocalDateTime fechaHora;
        private EstadoEvento estado;
        private Recinto recinto;

        public Builder(String nombre) {
            this.nombre = nombre;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder categoria(String categoria) {
            this.categoria = categoria;
            return this;
        }


        public Builder fechaHora(LocalDateTime fechaHora) {
            this.fechaHora = fechaHora;
            return this;
        }

        public Builder estado(EstadoEvento estado) {
            this.estado = estado;
            return this;
        }

        public Builder recinto(Recinto recinto) {
            this.recinto = recinto;
            return this;
        }

        public Evento build() {
            return new Evento(this);
        }
    }
}
