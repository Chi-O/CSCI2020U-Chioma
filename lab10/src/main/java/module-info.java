module lab10.lab10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab10.lab10 to javafx.fxml;
    exports lab10.lab10;
}