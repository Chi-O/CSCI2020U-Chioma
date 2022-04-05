package csci2020u.lab09;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, CsvValidationException {
        // downloadStockPrices("AAPL");

        // use BorderPane layout
        BorderPane root = new BorderPane();

        ArrayList<Float> ticker1 = getClosingPrice("GOOG");
        ArrayList<Float> ticker2 = getClosingPrice("AAPL");

        LineChart<String, Number> line = drawLinePlot(ticker1, ticker2);

        root.setCenter(line);

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Lab 09 C: Stock Performance");
        stage.setScene(scene);
        stage.show();
    }

    // This function takes a stock ticker symbol (e.g. GOOG),
    // and downloads historical stock data about that organization from Yahoo Finance to the src/resources folder
    public static void downloadStockPrices(String ticker) throws IOException {
        String url = "https://query1.finance.yahoo.com/v7/finance/download/" + ticker + "?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
        String filePath = "src\\main\\resources\\" + ticker + ".csv";

        InputStream in = new URL(url).openStream();
        Files.copy(in, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Done downloading stock prices.");
    }

    // this helper function returns a list of dates from the downloaded stock data
    public static ArrayList<String> getStockDates() throws CsvValidationException, IOException {
        // load the csv
        // new CSV reader instance
        CSVReader csvReader = new CSVReader(new FileReader("src\\main\\resources\\GOOG.csv"));
        String[] nextRecord;

        ArrayList<String> dates = new ArrayList<String>(); // array to store dates

        // read csv line by line
        boolean headerFlag = true;
        while ((nextRecord = csvReader.readNext()) != null) {
            if (headerFlag) {
                headerFlag = false;
            } else {
                // System.out.println(nextRecord[0]);
                // get the column of dates
                dates.add(nextRecord[0]);
            }
        }

        // return the column of dates
        return dates;
    }

    // this helper function returns a list of floats of closing stock prices from the downloaded data
    public static ArrayList<Float> getClosingPrice(String ticker) throws IOException, CsvValidationException {
        // load the csv
        // new CSV reader instance
        String filePath = "src\\main\\resources\\" + ticker + ".csv";
        CSVReader csvReader = new CSVReader(new FileReader(filePath));
        String[] nextRecord;

        ArrayList<Float> closingPrices = new ArrayList<Float>(); // array to store dates

        // read csv line by line
        boolean headerFlag = true;
        while ((nextRecord = csvReader.readNext()) != null) {
            if (headerFlag) {
                headerFlag = false;
            } else {
                // System.out.println(nextRecord[0]);
                // get the column of closing prices
                closingPrices.add(Float.valueOf(nextRecord[4]));
            }

        }

        // return the column of dates
        return closingPrices;
    }

    // this function draws the line series of closing stock prices
    public static XYChart.Series<String, Number> plotLine(ArrayList<Float> closingTicker, String seriesName) throws CsvValidationException, IOException {
        XYChart.Series<String, Number> thisSeries = new XYChart.Series<>();
        thisSeries.setName(seriesName);
        // get y axis data i.e. dates
        ArrayList<String> dates = getStockDates();

        // add data to series
        for (int i = 0; i < closingTicker.size(); i++) {
            thisSeries.getData().add(new XYChart.Data<>(dates.get(i), closingTicker.get(i)));
        }

        return thisSeries;
    }

    // This function takes two lists of floating point values, which are stock closing price values
    // Use 2D graphics to draw the x-axis and y-axis 50 pixels from the left and bottom edge of the window
    // Call plotLine() (below) twice, once for each stock
    public static LineChart<String, Number> drawLinePlot(ArrayList<Float> closingTicker1, ArrayList<Float> closingTicker2) throws CsvValidationException, IOException {
        // numeric Y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Closing Price");

        // categorical X axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        // create a LineChart object and set title
        LineChart<String, Number> line = new LineChart<>(xAxis, yAxis);
        line.setTitle("Closing Stock Prices");

        // first series for first stock
        // call plotLine()
        XYChart.Series<String, Number> line1 = plotLine(closingTicker1, "GOOG");

        // second series for second stock
        // call plotLine()
        XYChart.Series<String, Number> line2 = plotLine(closingTicker2, "AAPL");

        // add the series to the LineChart
        line.getData().addAll(line1, line2);

        line.setLegendVisible(true);
        line.setCreateSymbols(false);
        line.setLegendSide(Side.TOP);

        // return line to add to layout
        return line;
    }

    public static void main(String[] args) {
        launch();
    }
}