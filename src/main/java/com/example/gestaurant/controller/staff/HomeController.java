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

    public void staffPage(){
        clear();
    }

    public void financePage(){
        clear();
        boxGlobalStaff.getChildren().add(financePage);

    }


    public void orderPage(){
        clear();
        boxGlobalStaff.getChildren().add(orderPage);

    }

    public void employeePage(){
        clear();
        boxGlobalStaff.getChildren().add(employeePage);

    }



    public void clear(){
        boxGlobalStaff.getChildren().remove(employeePage);
        boxGlobalStaff.getChildren().remove(financePage);
        boxGlobalStaff.getChildren().remove(orderPage);
    }

 
    public void quit(){
        StatusDb.changeStatus(false);
        Platform.exit();
    }

}