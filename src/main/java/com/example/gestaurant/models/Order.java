package com.example.gestaurant.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Order {

    private String tableId;
    private ArrayList <String> dishesId;
    private Date created;
    private String status;
    private int price;
    private String id;

    private String customer;
    public Order(String tableId,ArrayList<String> dishes, float price, String customer) {
        this.tableId = tableId;
        this.dishesId = dishes;
        this.created = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        this.status = "pending";
        this.price = (int) (price * 100);
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDishesId(ArrayList<String> dishesId) {
        this.dishesId = dishesId;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public Date getCreated() {
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
