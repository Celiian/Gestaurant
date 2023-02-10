package com.example.gestaurant.models;

import com.example.gestaurant.db.StatusDb;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class TimerThread extends Thread {
    private final Label label;

    public TimerThread(Label label) {
        this.label = label;
    }

    @Override
    public void run() {
        int min = 25;
        for (int sec = 0; min >= 0; sec --) {

            if (min == 15)
            {
                StatusDb.changeStatus(false);
            }
            if (sec == 0){
                min --;
                sec = 59;
            }
            String finalSec = String.valueOf(sec);
            String finalMin = String.valueOf(min);
            String minute;
            String second;
            if (sec < 10){
                second = "0" + finalSec;
            } else {
                second = finalSec;
            }
            if (min < 10){
                minute = "0" + finalMin;
            } else {
                minute = finalMin;
            }
            Platform.runLater(() -> label.setText(minute + ":" + second));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}