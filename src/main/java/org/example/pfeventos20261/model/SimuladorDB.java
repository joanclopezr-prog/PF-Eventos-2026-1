package org.example.pfeventos20261.model;

import org.example.pfeventos20261.model.logisticaEvento.Evento;
import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.Zona;
import org.example.pfeventos20261.model.logisticaVentas.Compra;
import org.example.pfeventos20261.model.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;

public final class SimuladorDB {

    public static List<Usuario> usuarios = new ArrayList<>();
    public static List<Evento> eventos = new ArrayList<>();
    public static List<Compra> compras = new ArrayList<>();
    public static List<Recinto> recintos = new ArrayList<>();
    private static SimuladorDB instance;

    public static SimuladorDB getInstance(){
        if(instance == null){
            instance = new SimuladorDB();
        }
        return instance;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void addZonaRecinto(Recinto recinto, Zona zona){
        int index = recintos.indexOf(recinto);
        List<Zona> newListZonas = recintos.get(index).getZonas();
        newListZonas.add(zona);
        recintos.get(index).setZonas(newListZonas);
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public List<Recinto> getRecintos() {
        return recintos;
    }

    public void addUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void removeUsuario(Usuario usuario){
        usuarios.remove(usuario);
    }

    public void updateUsuario(Usuario viejo, Usuario nuevo){
        int index = usuarios.indexOf(viejo);
        if(index != -1){
            usuarios.set(index, nuevo);
        }
    }

    public void addEvento(Evento evento){
        eventos.add(evento);
    }

    public void removeEvento(Evento evento){
        eventos.remove(evento);
    }

    public void updateEvento(Evento viejo, Evento nuevo){
        int index = eventos.indexOf(viejo);
        if(index != -1){
            eventos.set(index, nuevo);
        }
    }

    public void addCompra(Compra compra){
        compras.add(compra);
    }

    public void removeCompra(Compra compra){
        compras.remove(compra);
    }

    public void updateCompra(Compra vieja, Compra nueva){
        int index = compras.indexOf(vieja);
        if(index != -1){
            compras.set(index, nueva);
        }
    }
    public void addRecinto(Recinto recinto){
        recintos.add(recinto);
    }

    public void removeRecinto(Recinto recinto){
        recintos.remove(recinto);
    }

    public void updateRecinto(Recinto viejo, Recinto nuevo){
        int index = recintos.indexOf(viejo);
        if(index != -1){
            recintos.set(index, nuevo);
        }
    }


}
