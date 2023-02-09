package com.example.gestaurant.models;

import com.example.gestaurant.db.MongoDb;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
public class addTables {
    public static void main() {
        MongoDb.initiateDb();
        MongoClient mongoClient = MongoClients.create(MongoDb.url);
        MongoDatabase database = mongoClient.getDatabase("gestaurant");
        MongoCollection<Document> collection = database.getCollection("Tables");
        tables tables = new tables(3, "table3.pnj", 8, "salle", "");
        Document document = (new Document("number", tables.getNumber())
                .append("picture", tables.getPicture())
                .append("size", tables.getSize())
                .append("emplacement", tables.getEmplacement())
                .append("customer-name", tables.getCustomer()));
        collection.insertOne(document);
        mongoClient.close();
    }
}