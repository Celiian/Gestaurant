package com.example.gestaurant.controller.client;

import com.example.gestaurant.db.TableDb;
import com.example.gestaurant.models.Dish;
import com.example.gestaurant.models.Table;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import org.bson.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TableController implements Initializable {

    @FXML
    private ChoiceBox emplacementChoice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> stringTableList = TableDb.getTables();
        List<Table> tableList = new ArrayList<Table>();
        stringTableList.stream().map(Document::parse).forEach(table -> {
            tableList.add(new Table((Integer) table.get("number"), (String) table.get("image"), (Integer) table.get("size"), (String) table.get("emplacement")));
        });

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

}

