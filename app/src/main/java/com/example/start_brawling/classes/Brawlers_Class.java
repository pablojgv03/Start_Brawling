package com.example.start_brawling.classes;

public class Brawlers_Class {
    private String id;
    private String name;
    private String efoto;
    private String descripcion;
    private String winRate;

    public Brawlers_Class(String id, String winRate) {
        this.id = id;
        this.winRate = winRate;
    }

    public Brawlers_Class(String id, String name, String efoto, String descripcion) {
        this.id = id;
        this.name = name;
        this.efoto = efoto;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Brawlers_Class{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", efoto='" + efoto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public String getWinRate() {
        return winRate;
    }

    public void setWinRate(String winRate) {
        this.winRate = winRate;
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

    public String getEfoto() {
        return efoto;
    }

    public void setEfoto(String efoto) {
        this.efoto = efoto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
