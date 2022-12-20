package com.avion.flotteavion.models;

public class Kilometrage {
    int idAvion;
    String date;
    Double debut;
    Double fin;

    public Kilometrage() {
    }

    public Kilometrage(int idAvion, String date, Double debut, Double fin) {
        this.idAvion = idAvion;
        this.date = date;
        this.debut = debut;
        this.fin = fin;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getDebut() {
        return debut;
    }

    public void setDebut(Double debut) {
        this.debut = debut;
    }

    public Double getFin() {
        return fin;
    }

    public void setFin(Double fin) {
        this.fin = fin;
    }
}
