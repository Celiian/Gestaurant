package com.example.gestaurant.models;

public class OrderClient {

    public static int table;

    public static String name = "";


    public static int getTable() {
        return table;
    }

    public static void setTable(int table) {
        OrderClient.table = table;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        OrderClient.name = name;
    }

}
