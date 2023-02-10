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
    public static String url;

    //  initialisation of the database connection with the url in the properties file
    public static void initiateDb() {
        try (InputStream input = new FileInputStream(".properties")) {

            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("db.url");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}