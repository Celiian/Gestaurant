package com.example.gestaurant.controller.client;

import com.example.gestaurant.db.StatusDb;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class HomeController implements Initializable {

    @FXML
    private VBox boxGlobalClient;

    @FXML
    private VBox menuPage;

    @FXML
    private VBox tablePage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clear();
    }

    public void dishPage(){
        clear();
        boxGlobalClient.getChildren().addAll(menuPage);
    }


    public void tablePage(){
        clear();
        boxGlobalClient.getChildren().addAll(tablePage);
    }


    public void clear(){
        boxGlobalClient.getChildren().removeAll(menuPage);
        boxGlobalClient.getChildren().removeAll(tablePage);
    }


    public void quit(){
        Platform.exit();
    }

}