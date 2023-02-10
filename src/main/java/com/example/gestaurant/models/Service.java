package com.example.gestaurant.models;

import java.util.ArrayList;

public class Service {

    public static int totalCost;

    public static int totalGain;


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
