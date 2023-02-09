module com.example.gestaurant {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;

    opens com.example.gestaurant to javafx.fxml;
    exports com.example.gestaurant;

    opens com.example.gestaurant.db to javafx.fxml;
    exports com.example.gestaurant.db;
    exports com.example.gestaurant.controller;
    opens com.example.gestaurant.controller to javafx.fxml;
}