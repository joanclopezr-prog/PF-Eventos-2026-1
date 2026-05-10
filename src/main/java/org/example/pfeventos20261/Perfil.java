package org.example.pfeventos20261;


import org.example.pfeventos20261.model.usuario.Persona;

public final class Perfil {

    public static Perfil instance;
    public Persona perfilPersona;

    public static Perfil getInstance(){
        if(instance == null) instance = new Perfil();
        return instance;
    }

    public void setPerfilPersona(Persona persona){
        perfilPersona = persona;
    }

    public Persona getPerfilPersona() {
        return perfilPersona;
    }

}
