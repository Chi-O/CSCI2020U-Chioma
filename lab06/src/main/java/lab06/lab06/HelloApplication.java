package lab06.lab06;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // use FlowPane layout
        FlowPane root = new FlowPane();

        // data for bar chart
        double[] avgHousingPricesByYear = { 247381.0,264171.4,287715.3,294736.1, 308431.4,322635.9,340253.0,363153.7 };
        double[] avgCommercialPricesByYear = { 1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3 };

        // numeric Y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Average Prices");

        // numeric X axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Year");

        // create bar chart object and set title
        BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Average Commercial and Housing Prices by Year");

        // first series
        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.setName("Housing Prices");
        // add data to series
        for (int i = 1; i < 9; i++) {
            String year = "year " + Integer.toString(i);
            series1.getData().add(new XYChart.Data<String, Number>(year, avgHousingPricesByYear[i - 1]));
        }

        // second series
        XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
        series2.setName("Commercial Prices");
        // add data to series
        for (int i = 1; i < 9; i++) {
            String year = "year " + Integer.toString(i);
            series2.getData().add(new XYChart.Data<String, Number>(year, avgCommercialPricesByYear[i - 1]));
        }


        // add series to bar chart
        bc.getData().addAll(series1, series2);

        // change bar chart series color
        for (Node n: bc.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: red");
        }

        for (Node n_2: bc.lookupAll(".default-color1.chart-bar")) {
            n_2.setStyle("-fx-bar-fill: #0055ff");
        }
        bc.setLegendVisible(false);

        // add barchart to layout
        root.getChildren().add(bc);

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