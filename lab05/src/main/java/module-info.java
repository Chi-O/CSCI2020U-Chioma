module csci2020u.lab05.lab05 {
    requires javafx.controls;
    requires javafx.fxml;


    opens csci2020u.lab05.lab05 to javafx.fxml;
    exports csci2020u.lab05.lab05;
}