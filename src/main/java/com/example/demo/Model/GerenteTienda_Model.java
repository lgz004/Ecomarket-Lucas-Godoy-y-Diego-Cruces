package com.example.demo.Model;

public class GerenteTienda_Model extends Usuario_Model {
    private int idGerente;
    private String tiendaAsignada;

    public GerenteTienda_Model() {}

    public GerenteTienda_Model(String nombre, String email, int idGerente, String tiendaAsignada) {
        super(nombre, email);
        this.idGerente = idGerente;
        this.tiendaAsignada = tiendaAsignada;
    }

    public int getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(int idGerente) {
        this.idGerente = idGerente;
    }

    public String getTiendaAsignada() {
        return tiendaAsignada;
    }

    public void setTiendaAsignada(String tiendaAsignada) {
        this.tiendaAsignada = tiendaAsignada;
    }
}