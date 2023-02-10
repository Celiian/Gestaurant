package com.example.gestaurant.models;

import java.util.ArrayList;

public class Dish {

    private String id;
    private String name;
    private ArrayList <String> ingredients;
    private String image;
    private int price;
    private int cost;
    private String status;
    private String category;

    @Override
    public String toString() {
        return "Dish{" +
                "name: '" + name + '\'' +
                ", ingredients: " + ingredients +
                ", image: '" + image + '\'' +
                ", price: " + price +
                ", cost: " + cost +
                ", status: '" + status + '\'' +
                ", category: '" + category + '\'' +
                '}';
    }

    public Dish(String name, ArrayList<String> ingredients, String image, int price, int cost, String status, String category, String id) {
        this.name = name;
        this.ingredients = ingredients;
        this.image = image;
        this.price = price;
        this.cost = cost;
        this.status = status;
        this.category = category;
        this.id = id;
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

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public int getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
