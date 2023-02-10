package com.example.gestaurant;

import com.example.gestaurant.db.DishDb;
import com.example.gestaurant.db.MongoDb;
import com.example.gestaurant.db.StatusDb;
import com.example.gestaurant.db.TableDb;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MongoDb.initiateDb();
        TableDb.cleanTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestaurant.exe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}