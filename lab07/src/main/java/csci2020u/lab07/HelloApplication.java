package csci2020u.lab07;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // COUNT THE FREQUENCY OF EACH TYPE OF WARNING -----------------------------------------------------
        // create a FileReader object
        FileReader fr = new FileReader("src\\main\\resources\\weatherwarnings-2015.csv");
        // create a Buffered  object and pass FileReader as parameter
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        // HashMap object to store warning and their counts
        HashMap<String, Integer> warningCounts = new HashMap<String, Integer>();

        // read line by line
        while ((line = br.readLine()) != null) {
            // split the line by a comma
            String[] warning = line.split(",");
            String thisWarning = warning[5];

            if (warningCounts.containsKey(thisWarning)) {
                // if this type of warning is already in the map, increment the count
                warningCounts.put(thisWarning, warningCounts.get(thisWarning) + 1);
            } else {
                // otherwise, initialize the count of this type of warning to 1
                warningCounts.put(thisWarning, 1);
            }
        }

//        // FOR DEBUGGING PURPOSES: PRINT THE HASHMAP
//        for (Map.Entry entry<String, Integer> : warningCounts.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }

        // USE THIS DATA TO DISPLAY A PIE CHART ----------------------------------------------------------
        // list to hold data for pie chart
        ObservableList<PieChart.Data> pieData = FXCollections.observableList(new ArrayList<PieChart.Data>());

        for (Map.Entry<String, Integer> entry : warningCounts.entrySet()) {
            pieData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        // set up pie chart
        PieChart pc = new PieChart(pieData);
        pc.setClockwise(false);
        pc.setLegendSide(Side.LEFT);
        pc.setLabelsVisible(false);

        // add root layout
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #E6E6FA");
        // add pie chart to layout
        root.getChildren().add(pc);

        Scene scene = new Scene(root, 800, 400);

        stage.setTitle("C Lab 7");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}