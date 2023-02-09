package com.example.gestaurant.controller;

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
    private VBox globalBox;

    @FXML
    private AnchorPane boxHome;

    @FXML
    private AnchorPane boxDishes;

    @FXML
    private Button customerDoor;

    @FXML
    private Button staffDoor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        globalBox.getChildren().removeAll(boxDishes);
    }

    public void customerPage(){
        globalBox.getChildren().removeAll(boxHome);
        globalBox.getChildren().addAll(boxDishes);
    }


    public void staffPage(){

    }


    public void clear(){

    }

}