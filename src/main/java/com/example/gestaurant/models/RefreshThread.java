package com.example.gestaurant.models;

import com.example.gestaurant.controller.staff.OrderController;
import com.example.gestaurant.db.DishDb;
import com.example.gestaurant.db.OrderDb;
import com.example.gestaurant.db.StatusDb;
import com.example.gestaurant.db.TableDb;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RefreshThread extends Thread {

    private VBox orderListBox;

    private VBox ordersDone;

    public RefreshThread(VBox orderListBox, VBox ordersDone) {
        this.orderListBox = orderListBox;
        this.ordersDone = ordersDone;
    }

    private static List<Order> orderList = new ArrayList<>();


    @Override
    public void run() {
        while (true) {
            List<String> stringOrderList = OrderDb.getOrdersOpen();
            final int[] i = {0};
            stringOrderList.stream().map(Document::parse).forEach(order -> {
                orderList.add(new Order((String) order.get("table"), (ArrayList<String>) order.get("dishes"), 0, (String) order.get("customer")));
                orderList.get(i[0]).setCreated((Date) order.get("created"));
                orderList.get(i[0]).setStatus((String) order.get("status"));
                orderList.get(i[0]).setPrice((Integer) order.get("price"));
                orderList.get(i[0]).setId(order.get("_id").toString());

                i[0]++;
            });

            orderList.stream().forEach(order -> {
                String table = TableDb.getATable(order.getTableId());
                Label tableLabel = new Label("Numéro de table : " + Document.parse(table).get("number"));
                Label tableEmplacementLabel = new Label("Salle de la table : " + Document.parse(table).get("emplacement"));
                Label dishesLabel = new Label("Nombre de plats :" + order.getDishesId().size());
                Button orderDone = new Button("Prêt");
                Button orderCancel = new Button("Annuler");

                HBox hboxOrder = new HBox(30);
                hboxOrder.getChildren().addAll(tableLabel, dishesLabel, orderDone, orderCancel);
                Platform.runLater(() ->
                        orderListBox.getChildren().addAll(
                                hboxOrder
                        ));
                orderDone.setOnMouseClicked(mouseEvent -> {
                    OrderDb.validOrder(order);
                    orderList.remove(order);
                    Platform.runLater(() ->
                            orderListBox.getChildren().remove(hboxOrder));

                    Label tableLabelDone = new Label("Table " + Document.parse(table).get("number"));
                    Label tableEmplacementLabelDone = new Label("  Salle : " + Document.parse(table).get("emplacement"));
                    Label dishesLabelDone = new Label("  Plats :" + order.getDishesId().size());

                    if (ordersDone.getChildren().size() == 5) {
                        ordersDone.getChildren().remove(0);
                    }

                    ordersDone.getChildren().addAll(
                            new HBox(
                                    tableLabelDone,
                                    tableEmplacementLabelDone,
                                    dishesLabelDone)
                    );


                    DishDb.getDishesPrice(order.getDishesId());
                });

                orderCancel.setOnMouseClicked(mouseEvent -> {
                    orderList.remove(order);
                    orderListBox.getChildren().remove(hboxOrder);
                    OrderDb.deleteOrder(order.getId());
                });
            });
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}