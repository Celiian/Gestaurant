package com.example.gestaurant.controller.client;

import com.example.gestaurant.db.StatusDb;
import com.example.gestaurant.db.TableDb;
import com.example.gestaurant.models.OrderClient;
import javafx.application.Platform;
import com.example.gestaurant.models.TableGestaurant;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.bson.Document;

import java.net.URL;
import java.util.*;

public class TableController implements Initializable {

    @FXML
    private ChoiceBox emplacementChoice;

    @FXML
    private TextField fieldNumberCustomer;

    @FXML
    private TextField fieldNameCustomer;

    @FXML
    private Label errorLabel;
    @FXML
    private Label labelReserved;
    @FXML
    private Button tableSearch;
    @FXML
    private Button leaveBtn;
    @FXML
    private Label labelNb;
    @FXML
    private Label labelName;
    private static List<TableGestaurant> tableList = new ArrayList<>();

    //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> stringTableList = TableDb.getTables();
        //transform string to Document
        stringTableList.stream().map(Document::parse).forEach(table -> {
            tableList.add(new TableGestaurant((Integer) table.get("number"), (String) table.get("image"), (Integer) table.get("size"), (String) table.get("emplacement"), table.get("_id").toString(), (String) table.get("customer")));
        });
        emplacementChoice.setValue("Ou voulez vous manger ?");
        //transform tableList to emplacementList
        tableList.stream().map(TableGestaurant::getLocation).distinct().toList().forEach(emplacement -> {
            try {
                emplacementChoice.getItems().add(
                        emplacement
                );
            } catch (Exception e) {
                System.out.println(e);
            }
        });


    }

    //find out if a table is free depending on the number of people, location and availability
    public void searchTable() {

        if (fieldNumberCustomer.getText() != null || fieldNameCustomer.getText() != null) {
            try {
                errorLabel.setVisible(false);
                int customerNumber = Integer.parseInt(fieldNumberCustomer.getText());
                String customerName = fieldNameCustomer.getText();
                String emplacement = (String) emplacementChoice.getValue();
                List<TableGestaurant> validTableList = tableList.stream()
                        .filter(table -> table.getSize() >= customerNumber
                                && Objects.equals(table.getLocation(), emplacement) && Objects.equals(table.getCustomer(), "empty"))
                        .sorted(Comparator.comparing(TableGestaurant::getSize)).toList();
                //if there is no table available we display a message
                if (validTableList.size() == 0){
                    labelReserved.setText("Nous sommes désolés, nous n'avons aucune table disponible dans cette salle");
                }
                else {
                    //else we set the table number and the customer name
                    OrderClient.setTable(validTableList.get(0).getNumber());
                    OrderClient.setTableId(tableList.get(0).getId());
                    OrderClient.setName(customerName);

                    fieldNameCustomer.setVisible(false);
                    fieldNumberCustomer.setVisible(false);
                    emplacementChoice.setVisible(false);
                    tableSearch.setVisible(false);
                    labelNb.setVisible(false);
                    labelName.setVisible(false);

                    TableDb.JoinLeaveTable(validTableList.get(0).getId(), false);
                    labelReserved.setText("Vous avez réservé la table " + OrderClient.getTable());
                    leaveBtn.setVisible(true);
                }
            } catch (Exception e) {
                //if there is an error we display a message
                System.out.println(e);
                errorLabel.setVisible(true);
                fieldNameCustomer.setVisible(true);
                fieldNumberCustomer.setVisible(true);
                emplacementChoice.setVisible(true);
                tableSearch.setVisible(true);
                labelNb.setVisible(true);
                labelName.setVisible(true);
            }
        }
    }


    //leave the table
    public void leave(){
        TableDb.JoinLeaveTable(OrderClient.getTableId(), true);

        Platform.exit();
    }
}
