package com.example.gestaurant.controller.client;

import com.example.gestaurant.db.DishDb;
import com.example.gestaurant.models.Order;
import com.example.gestaurant.models.OrderClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.bson.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MenuController implements Initializable {

    @FXML
    private VBox menuBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> dishList = DishDb.getDishes();

        dishList.stream().map(Document::parse).forEach(dish -> {
            long variable = Long.parseLong(dish.get("price").toString());
            float price = (float) variable / 100;
            ArrayList<String> ingredientList = (ArrayList<String>) dish.get("ingredients");
            String ingredients = ingredientList.stream().map(Object::toString).collect(Collectors.joining(", "));
            if (!Objects.equals(dish.get("status"), "out of stock") && !Objects.equals(dish.get("status"), "coming soon")) {
                HBox hbox = new HBox(50);
                hbox.getChildren().addAll(
                        new Label((String) dish.get("name")),
                        new Label(ingredients),
                        new Label("Prix : " + price + "€")
                );
                try {
                    menuBox.getChildren().addAll(
                            hbox
                    );
                }
                catch (Exception e){
                    System.out.println(e);
                }

            }

        });
    }
    public void test(){
        System.out.println(OrderClient.getTable());
        System.out.println(OrderClient.getName());
    }
}
