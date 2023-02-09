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



public class MongoDb {
    public static MongoDatabase database;
    private static String url;

    public static void initiateDb() {
        try (InputStream input = new FileInputStream(".properties")) {

            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("db.url");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getOrders() {
        try (MongoClient mongoClient = MongoClients.create(url)) {
            database = mongoClient.getDatabase("gestaurant");
            MongoCollection<Document> collection = database.getCollection("Orders");

            Document doc = collection.find().first();
            if (doc != null) {
                return doc.toJson();
            } else {
                return "No orders.";
            }
        }
    }
}