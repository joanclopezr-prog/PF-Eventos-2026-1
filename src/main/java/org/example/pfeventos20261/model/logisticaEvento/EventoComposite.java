package org.example.pfeventos20261.model.logisticaEvento;

import java.util.ArrayList;
import java.util.List;

public class EventoComposite implements IEvento{
    private List<IEvento> childrens = new ArrayList<>();


    @Override
    public List<IEvento> getHijos() {
        return childrens;
    }
}
