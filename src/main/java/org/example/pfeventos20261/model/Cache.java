package org.example.pfeventos20261.model;

import org.example.pfeventos20261.cache.CompraRepository;
import org.example.pfeventos20261.cache.EventoRepository;
import org.example.pfeventos20261.cache.RecintoRepository;
import org.example.pfeventos20261.model.usuario.Persona;

public final class Cache {
    private static Cache instance;
    private final EventoRepository eventoRepo = new EventoRepository();
    private final CompraRepository compraRepo = new CompraRepository();
    private final RecintoRepository recintoRepo = new RecintoRepository();

    private Persona usuario;

    private Cache() {}

    public static Cache getInstance(){
        if(instance == null) instance = new Cache();
        return instance;
    }

    public EventoRepository eventos() { return eventoRepo; }
    public CompraRepository compras() { return compraRepo; }
    public RecintoRepository recintos() { return recintoRepo; }

    public Persona getUsuario() { return usuario; }
    public void setUsuario(Persona usuario) { this.usuario = usuario; }
}
