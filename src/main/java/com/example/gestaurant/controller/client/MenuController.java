package com.example.gestaurant.controller.client;

import com.example.gestaurant.db.DishDb;
import com.example.gestaurant.db.OrderDb;
import com.example.gestaurant.db.StatusDb;
import com.example.gestaurant.models.Order;
import com.example.gestaurant.models.OrderClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    private VBox dishCart;

    @FXML
    private Label totalAmount;

    @FXML
    private Label errorOrder;

    @FXML
    private Label errorOpen;


    private float totalPrice = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> dishList = DishDb.getDishes();

        dishList.stream().map(Document::parse).forEach(dish -> {
            long variable = Long.parseLong(dish.get("price").toString());
            float price = (float) variable / 100;
            ArrayList<String> ingredientList = (ArrayList<String>) dish.get("ingredients");
            String ingredients = ingredientList.stream().map(Object::toString).collect(Collectors.joining(", "));
            if (!Objects.equals(dish.get("status"), "out of stock") && !Objects.equals(dish.get("status"), "coming soon")) {
                HBox hbox = new HBox();
                Label labelId = new Label(dish.get("_id").toString());
                labelId.setVisible(false);

                Label labelName = new Label((String) dish.get("name"));
                Label labelIngredients = new Label(ingredients);
                Label labelPrice = new Label("Prix : " + price + "€");
                Button buttonAdd = new Button("Choisir");

                labelName.setMinWidth(110);
                labelIngredients.setMinWidth(110);
                labelPrice.setMinWidth(90);
                labelId.setMaxWidth(1);
                ImageView image = new ImageView((String) dish.get("image"));
                image.setFitWidth(100);
                image.setFitHeight(100);
                HBox hboxInside = new HBox(7);
                hboxInside.getChildren().addAll(labelName,
                        labelIngredients,
                        labelPrice,
                        buttonAdd,
                        labelId);
                hbox.getChildren().addAll(
                        image,
                        hboxInside


                );
                hbox.setMinWidth(600);

                buttonAdd.setOnMouseClicked(event -> {

                    HBox hboxCart = new HBox();

                    Label labelNameCart = new Label(labelName.getText());
                    Label labelIngredientsCart = new Label(labelIngredients.getText());
                    Label labelPriceCart = new Label(labelPrice.getText());
                    Label labelIdCart = new Label(labelId.getText());
                    labelIdCart.setVisible(false);
                    Button buttonDelete = new Button("Retirer");

                    labelNameCart.setMinWidth(100);
                    labelIngredientsCart.setMinWidth(130);
                    labelPriceCart.setMinWidth(70);
                    labelIdCart.setMaxWidth(1);

                    hboxCart.getChildren().addAll(
                            new VBox(labelNameCart,
                                    labelIngredientsCart
                            ),
                            new VBox(
                                    labelPriceCart,
                                    buttonDelete,
                                    labelIdCart)
                    );

                    dishCart.getChildren().add(hboxCart);

                    totalPrice += price;
                    totalAmount.setText("TOTAL   -----------------   " + totalPrice +" €");
                    OrderClient.addToDishesList(labelIdCart.getText());

                    buttonDelete.setOnMouseClicked(eventDelete -> {
                        dishCart.getChildren().remove(hboxCart);
                        totalPrice -= price;
                        totalAmount.setText("TOTAL   -----------------   " + totalPrice +" €");
                        OrderClient.removeFromList(labelIdCart.getText());

                    });
                });

                try {
                    menuBox.getChildren().addAll(
                            hbox
                    );
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    public void orderDishes(){
        if(StatusDb.getStatus()) {
            if (OrderClient.getTableId() != null) {
                errorOrder.setVisible(false);
                Order order = new Order(OrderClient.getTableId(), OrderClient.getDishesListId(), totalPrice, OrderClient.getName());
                OrderDb.addOrder(order);
            } else {
                errorOrder.setVisible(true);
            }
        }
        else {
            errorOpen.setVisible(true);
        }
    }
}
