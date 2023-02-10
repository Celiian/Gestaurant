package com.example.gestaurant.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Order {

    private String tableId;
    private ArrayList <String> dishesId;
    private LocalDateTime created;
    private String status;
    private int price;

    public Order(String tableId,ArrayList<String> dishes, float price) {
        this.tableId = tableId;
        this.dishesId = dishes;
        this.created = LocalDateTime.now();
        this.status = "created";
        this.price = (int) (price * 100);
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public ArrayList<String> getDishesId() {
        return dishesId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getStatus() {
        return status;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Order {" +
                "dishes: " + dishesId +
                ", created: " + created +
                ", status: '" + status + '\'' +
                ", price: " + price +
                '}';
    }
}
