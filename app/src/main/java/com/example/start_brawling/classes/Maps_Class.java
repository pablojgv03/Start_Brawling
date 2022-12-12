package com.example.start_brawling.classes;

public class Maps_Class {
    private String id;
    private String name;
    private String efoto;
    private String modo;


    public Maps_Class(String id, String name, String efoto, String modo) {
        this.id = id;
        this.name = name;
        this.efoto = efoto;
        this.modo = modo;
    }


    @Override
    public String toString() {
        return "Brawlers_Class{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", efoto='" + efoto + '\'' +
                ", descripcion='" + modo + '\'' +
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

    public String getEfoto() {
        return efoto;
    }

    public void setEfoto(String efoto) {
        this.efoto = efoto;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }
}
