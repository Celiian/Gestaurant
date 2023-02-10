package com.example.gestaurant.db;

import com.example.gestaurant.models.Order;
import com.mongodb.BasicDBObject;
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

    //get all orders from the database
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


    //transform the order from pending to done
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

    //get all orders from the database with the status pending
    public static List<String> getOrdersOpen() {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            FindIterable<Document> collectionOrders= MongoDb.database.getCollection("Orders").find(eq("status", "pending"));
            List<String> orders = new ArrayList<>();
            //transform the order in json
            for (Document collectionOrder : collectionOrders) {
                orders.add(collectionOrder.toJson());
            }

            return orders;
        }

    }


    //add an order in the database
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

    //delete an order in the database
    public static void deleteOrder(String id) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            MongoDb.database.getCollection("Orders").deleteOne(new Document()
                    .append("_id", new ObjectId(id)));
        }
    }

    //get orders by id
    public static List<String> getOrdersById(ArrayList<String> ordersId) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            MongoCollection<Document> collectionOrders = MongoDb.database.getCollection("orders");

            ArrayList<ObjectId> ordersObjectId = new ArrayList<>();
            ordersId.stream().forEach(order -> {
                ordersObjectId.add(new ObjectId(order));
            });

            BasicDBObject inQuery = new BasicDBObject();
            inQuery.put("_id", new BasicDBObject("$in", ordersObjectId));
            FindIterable<Document> ordersDoc = collectionOrders.find(inQuery);

            //transform the order in json
            List<String> orders = new ArrayList<>();
            for (Document collectionOrder : ordersDoc) {
                orders.add(collectionOrder.toJson());
            }

            return orders;
        }
    }
}