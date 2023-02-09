package com.example.gestaurant.models;

public class tables {
    private int number;
    private String picture;
    private int size;
    private String emplacement;
    private String customer;

    public tables(int number, String picture, int size, String emplacement, String customer) {
        this.number = number;
        this.picture = picture;
        this.size = size;
        this.emplacement = emplacement;
        this.customer = customer;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getEmplacement() {
        return this.emplacement;
    }

    public void setEmplacement(String emplacement) {
        if (emplacement.equals("interieur")) {
            this.emplacement = emplacement;
        } else if (emplacement.equals("exterieur")) {
            this.emplacement = emplacement;
        } else {
            this.emplacement = "interieur";
        }
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String prenom) {
        this.customer = prenom;
    }
}