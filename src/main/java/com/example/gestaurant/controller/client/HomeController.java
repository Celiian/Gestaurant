package com.example.gestaurant.controller.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private VBox boxGlobalClient;

    @FXML
    private VBox menuPage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxGlobalClient.getChildren().removeAll(menuPage);

    }

    public void dishPage(){
        boxGlobalClient.getChildren().addAll(menuPage);
    }


    public void staffPage(){

    }


    public void clear(){

    }

}