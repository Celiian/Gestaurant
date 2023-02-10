package com.example.gestaurant.controller;

import com.example.gestaurant.db.StatusDb;
import javafx.application.Platform;
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

    @FXML
    private VBox staffBox;


    //initialize the page landing
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            clear();
            globalBox.getChildren().add(landingBox);
    }

    //clear the page and add the customer page
    public void customerPage(){
        clear();
        globalBox.getChildren().addAll(customerBox);
    }

    //clear the page and add the staff page
    public void staffPage(){
        clear();
        globalBox.getChildren().addAll(staffBox);
    }

    //clear all the page
    public void clear(){
        globalBox.getChildren().removeAll(customerBox);
        globalBox.getChildren().removeAll(landingBox);
        globalBox.getChildren().remove(staffBox);
    }

    //quit the application and change the status
    public void quit(){
        Platform.exit();
    }

}