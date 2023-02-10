package com.example.gestaurant.controller.staff;

import com.example.gestaurant.db.DishDb;
import com.example.gestaurant.db.OrderDb;
import com.example.gestaurant.db.StatusDb;
import com.example.gestaurant.db.TableDb;
import com.example.gestaurant.models.Order;
import com.example.gestaurant.models.RefreshThread;
import com.example.gestaurant.models.TimerThread;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.bson.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private Label timer;
    @FXML
    private Label service;
    @FXML
    private Button btnStart;
    @FXML
    private VBox orderListBox;
    @FXML
    private VBox ordersDone;

    private static List<Order> orderList = new ArrayList<>();

    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            orderListBox.getChildren().addAll(
                    hboxOrder
            );

            orderDone.setOnMouseClicked(mouseEvent -> {
                OrderDb.validOrder(order);
                orderList.remove(order);
                orderListBox.getChildren().remove(hboxOrder);
                Label tableLabelDone = new Label("Table " + Document.parse(table).get("number"));
                Label tableEmplacementLabelDone = new Label("  Salle : " + Document.parse(table).get("emplacement"));
                Label dishesLabelDone = new Label("  Plats :" + order.getDishesId().size());

                if (ordersDone.getChildren().size() == 5){
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
    }

    public void startService() {
        btnStart.setVisible(false);
        service.setVisible(true);
        StatusDb.changeStatus(true);
        TimerThread timerThread = new TimerThread(timer);
        timerThread.start();

        RefreshThread refreshThread = new RefreshThread(orderListBox, ordersDone);
        refreshThread.start();
    }
}
