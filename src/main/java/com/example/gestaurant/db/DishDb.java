package com.example.gestaurant.db;

import com.example.gestaurant.models.Dish;
import com.example.gestaurant.models.Service;
import com.example.gestaurant.models.TableGestaurant;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class DishDb {

    //get all dishes from the database
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

    //get all dishes price from the database
    public static void getDishesPrice(ArrayList<String> dishesId) {
        //try connect to the database and get the collection Dishes
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            MongoCollection<Document> collectionDishes = MongoDb.database.getCollection("Dishes");
            //convert the dishesId from String to ObjectId
            ArrayList<ObjectId> dishesObjectId = new ArrayList<>();
            dishesId.stream().forEach(dish -> {
                dishesObjectId.add(new ObjectId(dish));
            });


            BasicDBObject inQuery = new BasicDBObject();
            inQuery.put("_id", new BasicDBObject("$in", dishesObjectId));
            FindIterable<Document> dishesDoc = collectionDishes.find(inQuery);

            //add the price of each dish to the total gain
            dishesDoc.forEach(dish -> {
                        Service.addTotalGain((Integer) dish.get("price"));
                        Service.addTotalCost((Integer) dish.get("cost"));
                    });
        }
    }

    //get dish by id for the order
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

    //add dish to the database
    public static void addDish(Dish dish) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");

            Document document = new Document();
            document.append("name", dish.getName())
                    .append("ingredients", dish.getIngredients())
                    .append("image", dish.getImage())
                    .append("price", dish.getPrice())
                    .append("cost", dish.getCost());


            MongoDb.database.getCollection("Dishes").insertOne(document);
        }
    }
}