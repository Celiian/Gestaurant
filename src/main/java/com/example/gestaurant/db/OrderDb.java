package com.example.gestaurant.db;

import com.example.gestaurant.models.Order;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.List;


public class OrderDb {

    public static List<String> getOrders() {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            FindIterable<Document> collectionOrders= MongoDb.database.getCollection("Orders").find();

            List<String> orders = new ArrayList<>();
            for (Document collectionOrder : collectionOrders) {
                orders.add(collectionOrder.toJson());
            }

            return orders;
        }

    }


    public static void addOrder(Order order) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            MongoDb.database.getCollection("Orders").insertOne(new Document()
                    .append("table", order.getTableId())
                    .append("dishes", order.getDishesId())
                    .append("created", order.getCreated())
                    .append("status", order.getStatus())
                    .append("prie", order.getPrice()));
        }
    }
}