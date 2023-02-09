package com.example.gestaurant.db;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.List;


public class DishDb {

    public static List<String> getDishes() {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            FindIterable<Document> collectionDishes = MongoDb.database.getCollection("Dishes").find();

            List<String> dishes = new ArrayList<>();
            for (Document collectionDish : collectionDishes) {
                dishes.add(collectionDish.toJson());
            }

            return dishes;
        }

    }
}