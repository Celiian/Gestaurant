package com.example.gestaurant.db;

import com.example.gestaurant.models.Order;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;

public class StatusDb {

    public static boolean getStatus() {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            Document collectionOrders= MongoDb.database.getCollection("Status").find().first();
            return (boolean) collectionOrders.get("open");
        }
    }

    public static void changeStatus(boolean status) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            Document collectionOrders= MongoDb.database.getCollection("Status").find().first();
            boolean open = (boolean) collectionOrders.get("open");

            Bson filter = Filters.eq("open", open);
            Document document = new Document("open", status);
            MongoDb.database.getCollection("Status").replaceOne(filter, document);
        }
    }

}