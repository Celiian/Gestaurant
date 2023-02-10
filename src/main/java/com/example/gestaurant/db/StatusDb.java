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

public class StatusDb {

    public static List<String> getStatus() {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            Document collectionOrders= MongoDb.database.getCollection("Status").find().first();
            boolean b = Boolean.parseBoolean((String) collectionOrders.get("open"));
            return b;
        }

    }

}