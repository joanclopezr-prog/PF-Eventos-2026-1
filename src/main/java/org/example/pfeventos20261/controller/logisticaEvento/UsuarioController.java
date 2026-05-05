package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.Cache;
import org.example.pfeventos20261.model.usuario.Persona;
import org.example.pfeventos20261.model.usuario.Usuario;

public class UsuarioController {
    private Cache proxy;

    public UsuarioController(Cache proxy) {
        this.proxy = proxy;
    }

    public void setUsuario(Usuario usuario){
        proxy.setUsuario(usuario);
    }

    public Persona getUsuario(Persona persona){
        return proxy.getUsuario();
    }

}
