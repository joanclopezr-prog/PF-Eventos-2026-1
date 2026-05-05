package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.Cache;
import org.example.pfeventos20261.model.logisticaEvento.Evento;

import java.util.List;

public class EventoController {
    private Cache proxy;

    public EventoController(Cache proxy) {
        this.proxy = proxy;
    }

    public void addEvento(Evento evento){
        proxy.eventos().add(evento);
    }

    public void removeEvento(Evento evento){
        proxy.eventos().remove(evento);
    }

    public void updateEvento(Evento viejo, Evento nuevo){
        proxy.eventos().update(viejo,nuevo);
    }

    public List<Evento> getEventos(){
        return proxy.eventos().getAll();
    }
}
