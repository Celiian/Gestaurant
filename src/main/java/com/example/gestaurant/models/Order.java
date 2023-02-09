package com.example.gestaurant.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Order {

    //private Table table;
    private ArrayList <Dish> dishes;
    private LocalDateTime created;
    private String status;
    private int price;

    public Order(ArrayList<Dish> dishes) {
        this.dishes = dishes;
        this.created = LocalDateTime.now();
        this.status = "created";
        this.price = dishes.stream().map(Dish::getPrice).collect(Collectors.summingInt(Integer::intValue));
    }

    @Override
    public String toString() {
        return "Order {" +
                "dishes: " + dishes +
                ", created: " + created +
                ", status: '" + status + '\'' +
                ", price: " + price +
                '}';
    }
}
