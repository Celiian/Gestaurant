module com.example.gestaurant {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestaurant to javafx.fxml;
    exports com.example.gestaurant;
}