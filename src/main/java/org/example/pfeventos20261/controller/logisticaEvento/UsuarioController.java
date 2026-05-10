package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.Cache;
import org.example.pfeventos20261.model.usuario.Persona;
import org.example.pfeventos20261.model.usuario.Usuario;

import java.util.List;

public class UsuarioController {
    private Cache proxy;

    public UsuarioController(Cache proxy) {
        this.proxy = proxy;
    }

    public void setPersona(Persona persona){
        proxy.usuario().add(persona);
    }

    public List<Persona> getUsuario(){
        return proxy.usuario().getAll();
    }

}
