package lab06.lab06;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // use FlowPane layout
        FlowPane root = new FlowPane();

        // numeric Y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Average Prices");

        // numeric X axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Year");

        // create bar chart object and set title
        BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Average Commercial and Housing Prices by Year");



        // create scene
        Scene scene = new Scene(root, 550, 450);

        stage.setTitle("Lab 6 - 2D Graphics!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}