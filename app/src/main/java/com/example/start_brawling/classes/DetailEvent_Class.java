package com.example.start_brawling.classes;

import java.util.ArrayList;

public class DetailEvent_Class {
    private String id;
    private String name;
    private String mapa;
    private String modo;
    private String fechaIni;
    private String fechaFin;
    private String efoto;
    private ArrayList<Brawlers_Class> brawlerStats;


    public DetailEvent_Class(String id, String name, String mapa, String modo, String fechaIni, String fechaFin, String efoto, ArrayList brawlerStats) {
        this.id = id;
        this.name = name;
        this.mapa = mapa;
        this.modo = modo;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.efoto = efoto;
        this.brawlerStats = brawlerStats;
    }

    @Override
    public String toString() {
        return "DetailEvent_Class{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mapa='" + mapa + '\'' +
                ", modo='" + modo + '\'' +
                ", fechaIni='" + fechaIni + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                ", efoto='" + efoto + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEfoto() {
        return efoto;
    }

    public void setEfoto(String efoto) {
        this.efoto = efoto;
    }

}
