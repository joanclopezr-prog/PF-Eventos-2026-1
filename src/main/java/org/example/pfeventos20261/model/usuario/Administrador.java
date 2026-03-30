package org.example.pfeventos20261.model.usuario;

public class Administrador extends Persona {
    private String correo;

    public Administrador(String id, String nombreCompleto, String correo) {
        super(id, nombreCompleto);
        this.correo = correo;
    }
}