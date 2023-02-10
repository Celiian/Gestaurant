package com.example.gestaurant.db;

import com.example.gestaurant.models.Order;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;

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


    public static void validOrder(Order order){
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
        ;
            Bson filter = Filters.eq("_id", new ObjectId(order.getId()));
            Document document = new Document("status", "done").append("table", order.getTableId())
                    .append("dishes", order.getDishesId())
                    .append("created", order.getCreated())
                    .append("price", order.getPrice());
            UpdateResult result = MongoDb.database.getCollection("Orders").replaceOne(filter, document);
            System.out.println("Matched document count: " + result.getMatchedCount());
            System.out.println("Modified document count: " + result.getModifiedCount());
        }


    }

    public static List<String> getOrdersOpen() {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            FindIterable<Document> collectionOrders= MongoDb.database.getCollection("Orders").find(eq("status", "pending"));

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
                    .append("price", order.getPrice())
                    .append("customer", order.getCustomer()));
        }
    }


    public static void deleteOrder(String id) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            MongoDb.database.getCollection("Orders").deleteOne(new Document()
                    .append("_id", new ObjectId(id)));
        }
    }
}