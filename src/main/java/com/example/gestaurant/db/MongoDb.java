package com.example.gestaurant.db;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class MongoDb {
    public static MongoDatabase database;
    static String url;

    public static void initiateDb() {
        try (InputStream input = new FileInputStream(".properties")) {
            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("db.url");
            System.out.println("URL de la base de données : " + url);
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de propriétés n'a pas été trouvé");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la lecture du fichier de propriétés");
            e.printStackTrace();
        }
    }

/*
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
*/

    public static String getTable() {
        try (MongoClient mongoClient = MongoClients.create(url)){
            database = mongoClient.getDatabase("gestaurant");
            MongoCollection<Document> collection = database.getCollection("Tables");
            Document doc = collection.find().first();
            if (doc != null) {
                return doc.toJson();
            } else {
                return "No have Table.";
            }
        }
    }
}