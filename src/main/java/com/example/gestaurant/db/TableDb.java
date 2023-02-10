package com.example.gestaurant.db;

import com.example.gestaurant.models.OrderClient;
import com.example.gestaurant.models.Table;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.print.Doc;
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




    public static void JoinLeaveTable(String id, boolean leaving){
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            Document table = MongoDb.database.getCollection("Tables").find(eq("_id", new ObjectId(id))).first();
            Bson filter = Filters.eq("_id", new ObjectId(table.get("_id").toString()));
            Document document;
            if (leaving){
                document = new Document("customer", "empty");
            }
            else {
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
}