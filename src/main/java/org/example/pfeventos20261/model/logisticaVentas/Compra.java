package org.example.pfeventos20261.model.logisticaVentas;

import org.example.pfeventos20261.model.enums.EstadoCompra;
import org.example.pfeventos20261.model.logisticaEvento.Evento;
import org.example.pfeventos20261.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class Compra {
    private String idCompra;
    private Usuario usuario;
    private Evento evento;
    private LocalDateTime fechaCreacion;
    private double total;
    private EstadoCompra estado;
    private List<Entrada> entradas;
    private List<String> serviciosAdicionales; // VIP, Seguro, etc.

    public void calcularTotal() {
        // Sumar servicios adicionales aquí...
    }
}

