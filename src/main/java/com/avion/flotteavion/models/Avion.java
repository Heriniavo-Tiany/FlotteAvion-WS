package com.avion.flotteavion.models;

public class Avion {

    String id;
    int model;
    String number;
    String photo;

    public Avion() {
    }

    public Avion(String id, int model, String number, String photo) {
        this.id = id;
        this.model = model;
        this.number = number;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
