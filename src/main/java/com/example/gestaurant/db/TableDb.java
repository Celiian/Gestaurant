package com.example.gestaurant.db;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.List;


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
}