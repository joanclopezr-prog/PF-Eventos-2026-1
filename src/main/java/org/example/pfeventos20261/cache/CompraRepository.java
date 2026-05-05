package org.example.pfeventos20261.cache;

import org.example.pfeventos20261.model.logisticaEvento.Evento;
import org.example.pfeventos20261.model.logisticaVentas.Compra;

import java.util.ArrayList;
import java.util.List;

public class CompraRepository {
    private final List<Compra> compras = new ArrayList<>();

    public void add(Compra compras) { this.compras.add(compras); }
    public void remove(Compra compras) { this.compras.remove(compras); }
    public void update(Compra viejo, Compra nuevo) {
        int index = compras.indexOf(viejo);
        if(index != -1) compras.set(index, nuevo);
    }
    public List<Compra> getAll() { return compras; }
}
