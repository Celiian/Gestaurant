package com.example.gestaurant.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
public class addTable {
    public static void main() {
        MongoDb.initiateDb();
        MongoClient mongoClient = MongoClients.create(MongoDb.url);
        MongoDatabase database = mongoClient.getDatabase("gestaurant");
        MongoCollection<Document> collection = database.getCollection("Tables");
        Table Table = new Table(2, "table2.pnj", 2, "bar", "Sylvain");
        Document document = (new Document("number", Table.getNumber())
                .append("picture", Table.getPicture())
                .append("size", Table.getSize())
                .append("emplacement", Table.getEmplacement())
                .append("customer-name", Table.getCustomer()));
        collection.insertOne(document);
        mongoClient.close();
    }
}