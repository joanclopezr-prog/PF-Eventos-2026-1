package org.example.pfeventos20261.cache;

import org.example.pfeventos20261.model.logisticaEvento.Recinto;
import org.example.pfeventos20261.model.logisticaEvento.RecintoFachade;
import org.example.pfeventos20261.model.usuario.Persona;
import org.example.pfeventos20261.model.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private final List<Persona> usuarios = new ArrayList<>();

    public void add(Persona usuario) { usuarios.add(usuario); }
    public void remove(Persona usuario) { usuarios.remove(usuario); }
    public void update(Persona viejo, Persona nuevo) {
        int index = usuarios.indexOf(viejo);
        if(index != -1) usuarios.set(index, nuevo);
    }
    public List<Persona> getAll() { return usuarios; }

}
