package org.example.pfeventos20261.controller.logisticaEvento;

import org.example.pfeventos20261.model.SimuladorDB;
import org.example.pfeventos20261.model.usuario.Usuario;

public class UsuarioController {
    private SimuladorDB simuladorDB;

    public UsuarioController(SimuladorDB simuladorDB) {
        this.simuladorDB = simuladorDB;
    }

    public void addUsuario(Usuario usuario){
        simuladorDB.addUsuario(usuario);
    }

    public void removeUsuario(Usuario usuario){
        simuladorDB.removeUsuario(usuario);
    }

    public void updateUsuario(Usuario viejo, Usuario nuevo){
        simuladorDB.updateUsuario(viejo, nuevo);
    }
}
