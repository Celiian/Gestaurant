package com.example.gestaurant.controller.staff;

import com.example.gestaurant.models.TimerThread;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private Label timer;

    @FXML
    private Label service;
    @FXML
    private Button btnStart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void startService() {
        btnStart.setVisible(false);
        service.setVisible(true);

        TimerThread timerThread = new TimerThread(timer);
        timerThread.start();
    }
}
