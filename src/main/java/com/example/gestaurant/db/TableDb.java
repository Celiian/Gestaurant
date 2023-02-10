package com.example.gestaurant.db;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class TableDb {

    public static List<String> getTables() {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            FindIterable<Document> collectionTables = MongoDb.database.getCollection("Tables").find();

            List<String> tables = new ArrayList<>();
            for (Document collectionTable : collectionTables) {
                tables.add(collectionTable.toJson());
            }

            return tables;
        }

    }

    public static String getATable(String id) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            Document table = MongoDb.database.getCollection("Tables").find(eq("_id", new ObjectId(id))).first();
            return table.toJson();
        }
    }



    public static void cleanTable(){
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            FindIterable<Document> collectionTables = MongoDb.database.getCollection("Tables").find();

            List<String> tables = new ArrayList<>();
            for (Document collectionTable : collectionTables) {
                tables.add(collectionTable.toJson());
            }
        }
    }
}