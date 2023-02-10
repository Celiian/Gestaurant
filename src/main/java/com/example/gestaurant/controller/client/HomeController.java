package com.example.gestaurant.controller.client;

import javafx.application.Platform;
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

    @FXML
    private VBox tablePage;


    @Override
    // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clear();
    }

    public void dishPage(){
        //clear the page
        clear();
        //add the menu page
        boxGlobalClient.getChildren().addAll(menuPage);
    }


    public void tablePage(){
        clear();
        //add the table page
        boxGlobalClient.getChildren().addAll(tablePage);
    }


    public void clear(){
        //remove the menu page
        boxGlobalClient.getChildren().removeAll(menuPage);
        //remove the table page
        boxGlobalClient.getChildren().removeAll(tablePage);
    }


    //quit the application
    public void quit(){
        Platform.exit();
    }

}