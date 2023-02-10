package com.example.gestaurant.models;

import org.bson.Document;

public class Table {
    private int number;
    private String picture;
    private int size;
    private String location;
    private String customer;

    private String id;

    public Table(int number, String picture, int size, String location, String id) {
        this.number = number;
        this.picture = picture;
        this.size = size;
        this.location = location;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public String getPicture() {
        return picture;
    }

    public int getSize() {
        return size;
    }

    public String getLocation() {
        return location;
    }

    public String getCustomer() {
        return customer;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setSize(int size) {
        if (size >= 0){
            this.size = size;
        }
    }

    public void setLocation(String location) {
        this.location = location.toLowerCase();
    }

    public void setCustomer(String customerName) {
        this.customer = customer;
    }

}
