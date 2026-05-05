package org.example.pfeventos20261.cache;

import org.example.pfeventos20261.model.logisticaEvento.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoRepository {
    private final List<Evento> eventos = new ArrayList<>();

    public void add(Evento evento) { eventos.add(evento); }
    public void remove(Evento evento) { eventos.remove(evento); }
    public void update(Evento viejo, Evento nuevo) {
        int index = eventos.indexOf(viejo);
        if(index != -1) eventos.set(index, nuevo);
    }
    public List<Evento> getAll() { return eventos; }
}
