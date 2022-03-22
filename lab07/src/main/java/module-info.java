module csci2020u.lab07 {
    requires javafx.controls;
    requires javafx.fxml;


    opens csci2020u.lab07 to javafx.fxml;
    exports csci2020u.lab07;
}