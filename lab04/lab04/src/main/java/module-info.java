module com.example.lab04 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab04 to javafx.fxml;
    exports com.example.lab04;
}