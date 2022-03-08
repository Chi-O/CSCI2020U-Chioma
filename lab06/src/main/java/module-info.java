module lab06.lab06 {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab06.lab06 to javafx.fxml;
    exports lab06.lab06;
}