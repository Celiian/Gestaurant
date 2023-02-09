package com.example.gestaurant.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LandingController implements Initializable {

    @FXML
    private VBox globalBox;

    @FXML
    private VBox landingBox;


    @FXML
    private VBox customerBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        globalBox.getChildren().removeAll(customerBox);

    }

    public void customerPage(){
        globalBox.getChildren().removeAll(landingBox);
        globalBox.getChildren().addAll(customerBox);
    }


    public void staffPage(){

    }


    public void clear(){

    }

}