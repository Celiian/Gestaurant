package com.example.gestaurant.db;

import com.example.gestaurant.models.Employee;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDb {

    // get all employees
    public static List<String> getEmployees() {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            FindIterable<Document> collectionEmployees = MongoDb.database.getCollection("Employees").find();

            List<String> employees = new ArrayList<>();
            for (Document collectionEmployee : collectionEmployees) {
                employees.add(collectionEmployee.toJson());
            }

            return employees;
        }

    }

    // add a employee
    public static void addEmployee(Employee employee) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            MongoDb.database.getCollection("Employees").insertOne(new Document()
                    .append("firstname", employee.getFirstName())
                    .append("lastName", employee.getLastName())
                    .append("job", employee.getJob())
                    .append("phone", employee.getPhone()));
        }
    }

    //delete a employee
    public static void deleteEmployee(String id) {
        try (MongoClient mongoClient = MongoClients.create(MongoDb.url)) {
            MongoDb.database = mongoClient.getDatabase("gestaurant");
            MongoDb.database.getCollection("Employees").deleteOne(new Document()
                    .append("_id", new ObjectId(id)));
        }
    }
}