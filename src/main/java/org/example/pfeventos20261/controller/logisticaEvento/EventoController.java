package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.SimuladorDB;
import org.example.pfeventos20261.model.logisticaEvento.Evento;

public class EventoController {
    private SimuladorDB simuladorDB;

    public EventoController(SimuladorDB simuladorDB) {
        this.simuladorDB = simuladorDB;
    }

    public void addEvento(Evento evento){
        simuladorDB.addEvento(evento);
    }

    public void removeEvento(Evento evento){
        simuladorDB.removeEvento(evento);
    }

    public void updateEvento(Evento viejo, Evento nuevo){
        simuladorDB.updateEvento(viejo, nuevo);
    }

}
