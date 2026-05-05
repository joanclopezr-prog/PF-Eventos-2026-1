package org.example.pfeventos20261.cache;

import org.example.pfeventos20261.model.logisticaEvento.Evento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.RecintoFachade;

import java.util.ArrayList;
import java.util.List;

public class RecintoRepository {
    private final List<Recinto> recintos = new ArrayList<>();
//    private RecintoFachade recintoFachade;

    public void add(Recinto recinto) { recintos.add(recinto); }
    public void remove(Recinto recinto) { recintos.remove(recinto); }
    public void update(Recinto viejo, Recinto nuevo) {
        int index = recintos.indexOf(viejo);
        if(index != -1) recintos.set(index, nuevo);
    }
    public List<Recinto> getAll() { return recintos; }

    public RecintoFachade getFachade(Recinto recinto) {
        return new RecintoFachade(recinto);
    }
}
