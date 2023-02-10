package com.example.gestaurant.controller.staff;

import com.example.gestaurant.db.EmployeeDb;
import com.example.gestaurant.db.OrderDb;
import com.example.gestaurant.models.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.bson.Document;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldFirstName;
    @FXML
    private TextField fieldPhoneNumber;
    @FXML
    private TextField fieldJob;

    @FXML
    private VBox employeeCard;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<String> employeeList = EmployeeDb.getEmployees();
        employeeList.stream().map(Document::parse).forEach(employee -> {
            HBox hbox = new HBox(20);
            hbox.minWidth(400);
            Button delete = new Button("virer");
            hbox.getChildren().addAll(
                    new Label((String) employee.get("firstname")),
                    new Label((String) employee.get("job")),
                    new Label((String) employee.get("phone")),
                    delete
            );
            employeeCard.getChildren().add(hbox);

            delete.setOnMouseClicked(mouseEvent -> {
                employeeCard.getChildren().remove(hbox);
                EmployeeDb.deleteEmployee((String) employee.get("_id"));
            });
        });
        }


        public void addEmployee(){
            if ((fieldName.getText() != null) && (!Objects.equals(fieldName.getText(), ""))
                    && (fieldFirstName.getText() != null) && (!Objects.equals(fieldFirstName.getText(), ""))
                    && (fieldPhoneNumber.getText() != null) && (!Objects.equals(fieldFirstName.getText(), ""))
                    && (fieldJob.getText() != null) && (!Objects.equals(fieldJob.getText(), ""))) {

                Employee employee = new Employee(fieldFirstName.getText(), fieldName.getText(), fieldJob.getText(), fieldJob.getText());
                EmployeeDb.addEmployee(employee);

            }
        }
}
