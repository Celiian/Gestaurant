package com.example.gestaurant.models;

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
        int min = 0;
        for (int sec = 0; min <= 60; sec ++) {
            if (sec == 60){
                min ++;
                sec = 0;
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