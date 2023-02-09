package com.example.gestaurant.controller.client;

import com.example.gestaurant.db.DishDb;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.bson.Document;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.*;
public class MenuController implements Initializable {

    @FXML
    private VBox menuBoxLeft;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> dishList = DishDb.getDishes();

        dishList.stream().map(Document::parse)
                .forEach(dish -> {
                    System.out.println(dish);
                    menuBoxLeft.getChildren().addAll(
                        new Label((String) dish.get("name"))
                        );
                });

    }
}

