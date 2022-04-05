module csci2020u.lab09 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens csci2020u.lab09 to javafx.fxml;
    exports csci2020u.lab09;
}