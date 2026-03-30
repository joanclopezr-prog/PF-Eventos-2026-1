package org.example.pfeventos20261.model.logisticaVentas;

import org.example.pfeventos20261.model.enums.EstadoEntrada;
import org.example.pfeventos20261.model.logisticaEvento.Asiento;
import org.example.pfeventos20261.model.logisticaEvento.Zona;

public class Entrada {
    private String idEntrada;
    private Zona zona;
    private Asiento asiento;
    private double precioFinal;
    private EstadoEntrada estado;
}
