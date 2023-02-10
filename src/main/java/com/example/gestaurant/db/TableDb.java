package com.example.gestaurant.db;

import com.example.gestaurant.models.OrderClient;
import com.example.gestaurant.models.TableGestaurant;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class TableDb {

    // get all tables
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

    // get a table
    public static String getATable(String id) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            Document table = MongoDb.database.getCollection("Tables").find(eq("_id", new ObjectId(id))).first();
            return table.toJson();
        }
    }


    //Clean all tables
    public static void cleanTable() {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            FindIterable<Document> collectionTables = MongoDb.database.getCollection("Tables").find();

            List<String> tables = new ArrayList<>();
            for (Document table : collectionTables) {
                Bson filter = Filters.eq("_id", new ObjectId(table.get("_id").toString()));
                Document document;
                document = new Document("customer", "empty");
                document.append("size", table.get("size"))
                        .append("emplacement", table.get("emplacement"))
                        .append("number", table.get("number"))
                        .append("image", table.get("image"));

                MongoDb.database.getCollection("Tables").replaceOne(filter, document);
            }
        }
    }

    //affected a table to a customer and clean the table if the customer is leaving
    public static void JoinLeaveTable(String id, boolean leaving) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            Document table = MongoDb.database.getCollection("Tables").find(eq("_id", new ObjectId(id))).first();
            //filter to find the table to update
            Bson filter = Filters.eq("_id", new ObjectId(table.get("_id").toString()));
            Document document;
            //if the customer is leaving, the table is cleaned
            if (leaving) {
                document = new Document("customer", "empty");
            } else {
                //else the table is affected to the customer
                document = new Document("customer", OrderClient.getName());
            }

            document.append("size", table.get("size"))
                    .append("emplacement", table.get("emplacement"))
                    .append("number", table.get("number"))
                    .append("image", table.get("image"))
            ;


            UpdateResult result = MongoDb.database.getCollection("Tables").replaceOne(filter, document);
            System.out.println("Matched document count: " + result.getMatchedCount());
            System.out.println("Modified document count: " + result.getModifiedCount());

        }
    }

    public static void addTable(TableGestaurant table) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");

            Document document = new Document();
            document.append("size", table.getSize())
                    .append("emplacement", table.getLocation())
                    .append("number", table.getNumber());

            MongoDb.database.getCollection("Tables").insertOne(document);
        }
    }

}