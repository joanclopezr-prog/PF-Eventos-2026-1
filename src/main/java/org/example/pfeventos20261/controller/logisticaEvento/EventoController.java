package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.Proxy;
import org.example.pfeventos20261.model.logisticaEvento.Evento;

import java.util.List;

public class EventoController {
    private Proxy proxy;

    public EventoController(Proxy proxy) {
        this.proxy = proxy;
    }

    public void addEvento(Evento evento){
        proxy.addEvento(evento);
    }

    public void removeEvento(Evento evento){
        proxy.removeEvento(evento);
    }

    public void updateEvento(Evento viejo, Evento nuevo){
        proxy.updateEvento(viejo, nuevo);
    }

    public List<Evento> getEventos(){
        return proxy.getEventos();
    }

}
