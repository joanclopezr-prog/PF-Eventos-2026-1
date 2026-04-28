package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.Proxy;
import org.example.pfeventos20261.model.logisticaVentas.Compra;

public class CompraController {
    private Proxy proxy;

    public CompraController(Proxy proxy) {
        this.proxy = proxy;
    }

    public void addCompra(Compra compra){
        proxy.addCompra(compra);
    }

    public void removeCompra(Compra compra){
        proxy.removeCompra(compra);
    }

    public void updateCompra(Compra vieja, Compra nueva){
        proxy.updateCompra(vieja, nueva);
    }
}