package org.example.pfeventos20261.model.usuario;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona{
    private String correo;
    private String telefono;
    private List<String> metodosPago;

    public Usuario(String id, String nombre, String correo,String telefono) {
        super(id,nombre);
        this.correo = correo;
        this.telefono = telefono;
        this.metodosPago = new ArrayList<>();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<String> getMetodosPago() {
        return metodosPago;
    }

    public void setMetodosPago(List<String> metodosPago) {
        this.metodosPago = metodosPago;
    }
}