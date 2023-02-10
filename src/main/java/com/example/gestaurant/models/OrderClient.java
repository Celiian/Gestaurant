package com.example.gestaurant.models;

import java.util.ArrayList;

public class OrderClient {

    public static int table;

    public static String name = "";


    public static ArrayList<String> dishesListId = new ArrayList<>();


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


    public static ArrayList<String> getDishesListId() {
        return dishesListId;
    }

    public static void setDishesListId(ArrayList<String> dishesList) {
        OrderClient.dishesListId = dishesList;
    }

    public static void addToDishesList(String dishesListId) {
        OrderClient.dishesListId.add(dishesListId);
    }

    public static void removeFromList(String dishesListId) {
        OrderClient.dishesListId.remove(dishesListId);
    }


}
