package com.example.gestaurant.controller.client;

import com.example.gestaurant.db.TableDb;
import com.example.gestaurant.models.Order;
import com.example.gestaurant.models.OrderClient;
import com.example.gestaurant.models.Table;
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
    private Label labelNb;
    @FXML
    private Label labelName;
    private static List<Table> tableList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> stringTableList = TableDb.getTables();
        stringTableList.stream().map(Document::parse).forEach(table -> {
            tableList.add(new Table((Integer) table.get("number"), (String) table.get("image"), (Integer) table.get("size"), (String) table.get("emplacement"), table.get("_id").toString()));
        });
        emplacementChoice.setValue("Ou voulez vous manger ?");

        tableList.stream().map(Table::getLocation).distinct().toList().forEach(emplacement -> {
            try {
                emplacementChoice.getItems().add(
                        emplacement
                );
            } catch (Exception e) {
                System.out.println(e);
            }
        });


    }

    public void searchTable() {

        if (fieldNumberCustomer.getText() != null || fieldNameCustomer.getText() != null) {
            try {
                errorLabel.setVisible(false);
                int customerNumber = Integer.parseInt(fieldNumberCustomer.getText());
                String customerName = fieldNameCustomer.getText();
                String emplacement = (String) emplacementChoice.getValue();
                List<Table> validTableList = tableList.stream()
                        .filter(table -> table.getSize() >= customerNumber
                                && Objects.equals(table.getLocation(), emplacement))
                        .sorted(Comparator.comparing(Table::getSize)).toList();
                OrderClient.setTable(validTableList.get(0).getNumber());
                OrderClient.setTableId(tableList.get(0).getId());
                OrderClient.setName(customerName);

                fieldNameCustomer.setVisible(false);
                fieldNumberCustomer.setVisible(false);
                emplacementChoice.setVisible(false);
                tableSearch.setVisible(false);
                labelNb.setVisible(false);
                labelName.setVisible(false);
                labelReserved.setText("Vous avez réservé la table " + OrderClient.getTable());
            } catch (Exception e) {
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
}
