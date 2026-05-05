package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.Cache;
import org.example.pfeventos20261.model.logisticaVentas.Compra;

public class CompraController {
    private Cache proxy;

    public CompraController(Cache proxy) {
        this.proxy = proxy;
    }

    public void addCompra(Compra compra){
        proxy.compras().add(compra);
    }

    public void removeCompra(Compra compra){
        proxy.compras().remove(compra);
    }

    public void updateCompra(Compra vieja, Compra nueva){
        proxy.compras().update(vieja,nueva);
    }
}