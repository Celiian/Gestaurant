package com.example.gestaurant.controller.staff;

import com.example.gestaurant.models.employee;
import com.example.gestaurant.db.EmployeeDb;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.bson.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.jar.Attributes;


public class EmployeeController implements Initializable {

    @FXML
    private VBox employeeList;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnRemoveEmployee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> stringEmployeeList = EmployeeDb.getEmployees();
        final int[] i = {0};
        stringEmployeeList.forEach(employee -> {
            Document document = Document.parse(employee);
            Label label = new Label();
            label.setText("Name: " + document.getString("name") + " " + document.getString("lastName") +
                    " Birthday: " + document.getString("birthday") + " Job: " + document.getString("job") +
                    " Phone: " + document.getString("phone"));
            label.setId("employee" + i[0]);
            employeeList.getChildren().add(label);
            i[0]++;
        });

        btnAddEmployee.setOnAction(event -> {
            employee employee = new employee("Name", "Lastname", "Birthday", "Job", "Phone");
            employee.setName("Name");
            employee.setLastName("Lastname");
            employee.setBirthday("Birthday");
            employee.setJob("Job");
            employee.setPhone("Phone");
            EmployeeDb.addEmployee(employee);
            Label label = new Label();
            label.setText("Name: " + employee.getName() + " " + employee.getLastName() +
                    " Birthday: " + employee.getBirthday() + " Job: " + employee.getJob() +
                    " Phone: " + employee.getPhone());
            label.setId("employee" + i[0]);
            employeeList.getChildren().add(label);
            i[0]++;
        });
    }
}