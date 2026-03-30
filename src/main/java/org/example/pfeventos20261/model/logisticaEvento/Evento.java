package org.example.pfeventos20261.model.logisticaEvento;

import org.example.pfeventos20261.model.enums.EstadoEvento;
import java.time.LocalDateTime;

public class Evento {
    private String id;
    private String nombre;
    private String categoria;
    private String ciudad;
    private LocalDateTime fechaHora;
    private EstadoEvento estado;
    private Recinto recinto;

    public Evento(String id, String nombre, Recinto recinto) {
        this.id = id;
        this.nombre = nombre;
        this.recinto = recinto;
        this.estado = EstadoEvento.BORRADOR;
    }
}

