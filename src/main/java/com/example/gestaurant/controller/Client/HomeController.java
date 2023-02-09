package com.example.gestaurant.controller.Client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private VBox boxGlobalClient;

    @FXML
    private VBox boxDishes;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxGlobalClient.getChildren().removeAll(boxDishes);

    }

    public void dishPage(){
        boxGlobalClient.getChildren().addAll(boxDishes);
    }


    public void staffPage(){

    }


    public void clear(){

    }

}