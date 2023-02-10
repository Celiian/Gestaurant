package com.example.gestaurant.controller.staff;

import com.example.gestaurant.db.StatusDb;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private VBox boxGlobalStaff;

    @FXML
    private VBox orderPage;

    @FXML
    private VBox financePage;

    @FXML
    private VBox employeePage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clear();
    }

    //clear the page
    public void staffPage(){
        clear();
    }

    //clear and add the finance page
    public void financePage(){
        clear();
        boxGlobalStaff.getChildren().add(financePage);

    }

    //clear and add the order page
    public void orderPage(){
        clear();
        boxGlobalStaff.getChildren().add(orderPage);

    }

    public void employeePage(){
        clear();
        boxGlobalStaff.getChildren().add(employeePage);

    }

    //clear the page
    public void clear(){
        boxGlobalStaff.getChildren().remove(employeePage);
        boxGlobalStaff.getChildren().remove(financePage);
        boxGlobalStaff.getChildren().remove(orderPage);
    };


    //quit the application and change the status
    public void quit(){
        StatusDb.changeStatus(false);
        Platform.exit();
    }

}