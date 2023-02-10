package com.example.gestaurant.db;

import com.example.gestaurant.models.Service;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

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

    public static void getDishesPrice(ArrayList<String> dishesId) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            MongoCollection<Document> collectionDishes = MongoDb.database.getCollection("Dishes");

            ArrayList<ObjectId> dishesObjectId = new ArrayList<>();
            dishesId.stream().forEach(dish -> {
                dishesObjectId.add(new ObjectId(dish));
            });


            BasicDBObject inQuery = new BasicDBObject();
            inQuery.put("_id", new BasicDBObject("$in", dishesObjectId));
            FindIterable<Document> dishesDoc = collectionDishes.find(inQuery);


            dishesDoc.forEach(dish -> {
                        Service.addTotalGain((Integer) dish.get("price"));
                        Service.addTotalCost((Integer) dish.get("cost"));
                    });
        }
    }

    public static List<String> getDishesById(ArrayList<String> dishesId) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            MongoCollection<Document> collectionDishes = MongoDb.database.getCollection("Dishes");

            ArrayList<ObjectId> dishesObjectId = new ArrayList<>();
            dishesId.stream().forEach(dish -> {
                dishesObjectId.add(new ObjectId(dish));
            });


            BasicDBObject inQuery = new BasicDBObject();
            inQuery.put("_id", new BasicDBObject("$in", dishesObjectId));
            FindIterable<Document> dishesDoc = collectionDishes.find(inQuery);

            List<String> dishes = new ArrayList<>();
            for (Document collectionOrder : dishesDoc) {
                dishes.add(collectionOrder.toJson());
            }

            return dishes;
        }
    }
}