package com.example.gestaurant.controller.staff;

import com.example.gestaurant.models.Pdf;
import com.example.gestaurant.models.Service;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinanceController implements Initializable {

    @FXML
    private Label labelSpent;
    @FXML
    private Label labelSold;
    @FXML
    private Label labelGain;

    @FXML
    private TextField accesPath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelSpent.setText(String.valueOf(Service.getTotalGain()));
        labelSold.setText(String.valueOf(Service.getTotalCost()));
        labelGain.setText(String.valueOf(Service.getTotalCost() - Service.getTotalGain()));
    }



    public void GetPdf(){
        String path = accesPath.getText();
        try {
            Pdf.main(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
