package com.example.gestaurant.models;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

public class Service {

    public static int totalCost = 0;

    public static int totalGain = 0;


    public static ArrayList<String> ordersDone = new ArrayList<>();

    public static ArrayList<String> getOrdersDone() {
        return ordersDone;
    }

    public static void setOrdersDone(ArrayList<String> ordersDone) {
        Service.ordersDone = ordersDone;
    }

    public static void addOrdersDone(String ordersDone) {
        Service.ordersDone.add(ordersDone);
    }

    public static int getTotalCost() {
        return totalCost;
    }

    public static void setTotalCost(int totalCost) {
        Service.totalCost = totalCost;
    }

    public static void addTotalCost(int totalCost) {
        Service.totalCost += totalCost;
    }

    public static int getTotalGain() {
        return totalGain;
    }

    public static void setTotalGain(int totalGain) {
        Service.totalGain = totalGain;
    }

    public static void addTotalGain(int totalGain) {
        Service.totalGain += totalGain;
    }
}
