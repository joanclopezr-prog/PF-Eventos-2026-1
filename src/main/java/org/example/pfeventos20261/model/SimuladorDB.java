package org.example.pfeventos20261.model;

import org.example.pfeventos20261.model.enums.EstadoEvento;
import org.example.pfeventos20261.model.logisticaEvento.Evento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaVentas.Compra;
import org.example.pfeventos20261.model.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimuladorDB {

    public static List<Usuario> usuarios = new ArrayList<>();
    public static List<Evento> eventos = new ArrayList<>();
    public static List<Compra> compras = new ArrayList<>();
    public static List<Recinto> recintos = new ArrayList<>();

    public static void cargarDatosPrueba() {
        if (eventos.isEmpty()) {
            Recinto movistarArena = new Recinto("R1", "Movistar Arena", "Calle 123", Collections.emptyList());
            eventos.add(new Evento.Builder("fiesta").build());

            usuarios.add(new Usuario("U1", "Juan Perez", "juan@mail.com","dsfasvn"));
        }
    }

}