package com.example.gestaurant.db;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class mongodb {
    public static MongoDatabase database;
    public static void initiateDb() {
        String url = "";
        try (InputStream input = new FileInputStream(".properties")) {

            Properties prop = new Properties();

            prop.load(input);

            url = prop.getProperty("db.url");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (MongoClient mongoClient = MongoClients.create(url)) {
            database = mongoClient.getDatabase("gestaurant");
            System.out.println("Connected");
        }

    }

    public String getOrders() {
        MongoCollection<Document> collection = database.getCollection("orders");

        Document doc = collection.find().first();
        if (doc != null) {
           return doc.toJson();
        } else {
            return "No orders.";
        }
    }
}