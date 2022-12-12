package com.example.start_brawling.classes;

public class Events_Class {
    private String id;
    private String name;
    private String efoto;
    private String modo;
    private String disponibility;


    public Events_Class(String id, String name, String efoto, String modo, String disponibility) {
        this.id = id;
        this.name = name;
        this.efoto = efoto;
        this.modo = modo;
        this.disponibility = disponibility;
    }

    @Override
    public String toString() {
        return "Events_Class{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", efoto='" + efoto + '\'' +
                ", modo='" + modo + '\'' +
                ", disponibility='" + disponibility + '\'' +
                '}';
    }

    public String getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(String disponibility) {
        this.disponibility = disponibility;
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
