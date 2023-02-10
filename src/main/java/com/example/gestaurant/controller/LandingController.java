package com.example.gestaurant.controller;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            clear();
            globalBox.getChildren().add(landingBox);
    }

    public void customerPage(){
        clear();
        globalBox.getChildren().addAll(customerBox);
    }


    public void staffPage(){
        clear();
        globalBox.getChildren().addAll(staffBox);
    }

    public void clear(){
        globalBox.getChildren().removeAll(customerBox);
        globalBox.getChildren().removeAll(landingBox);
        globalBox.getChildren().remove(staffBox);
    }


    public void quit(){
        Platform.exit();
    }

}