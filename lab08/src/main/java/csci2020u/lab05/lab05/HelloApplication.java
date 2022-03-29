package csci2020u.lab05.lab05;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.opencsv.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static csci2020u.lab05.lab05.DataSource.getAllMarks;

public class HelloApplication extends Application {
    // store the currently viewed file's filename
    static String currentFilename;

    @Override
    public void start(Stage stage) throws IOException {
        // create File menu instance
        Menu fileMenu = new Menu("File");

        // items to File menu
        fileMenu.getItems().add(new MenuItem("New"));
        fileMenu.getItems().add(new MenuItem("Open"));
        fileMenu.getItems().add(new MenuItem("Save"));
        fileMenu.getItems().add(new MenuItem("Save As"));
        fileMenu.getItems().add(new MenuItem("Exit"));

        // create a MenuBar instance and add File menu
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        // get student records table view
        TableView<StudentRecord> tvStudentRecords = getStudentRecords();
        // use BorderPane as the root layout
        BorderPane root = new BorderPane();
        // add TableView to the root layout
        root.setCenter(tvStudentRecords);

        // HERE: add MenuBar to layout
        root.setTop(menuBar);
        // create scene
        Scene scene = new Scene(root, 550, 450);
        stage.setTitle("Lab 08 Solution C");
        stage.setScene(scene);
        stage.show();
    }

    public static TableView<StudentRecord> getStudentRecords() {
        // get student marks
        ObservableList<StudentRecord> marks = getAllMarks();

        // create a TableView object
        TableView<StudentRecord> tvStudentRecords;
        tvStudentRecords = new TableView<StudentRecord>(marks);

        // associate the columns with column headings
        // use cell factory to populate cells with data
        TableColumn<StudentRecord, String> studentIDColumn = new TableColumn<>("SID");
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));    // use the data name from data model
        tvStudentRecords.getColumns().add(studentIDColumn);                             // add column to table

        TableColumn<StudentRecord, String> assignmentsColumn = new TableColumn<>("Assignments");
        assignmentsColumn.setCellValueFactory(new PropertyValueFactory<>("assignmentsGrade"));    // use the data name from data model
        tvStudentRecords.getColumns().add(assignmentsColumn);                             // add column to table

        TableColumn<StudentRecord, String> midtermCol = new TableColumn<>("Midterm");
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("midtermGrade"));    // use the data name from data model
        tvStudentRecords.getColumns().add(midtermCol);                             // add column to table

        TableColumn<StudentRecord, String> examCol = new TableColumn<>("Final Exam");
        examCol.setCellValueFactory(new PropertyValueFactory<>("examGrade"));    // use the data name from data model
        tvStudentRecords.getColumns().add(examCol);                             // add column to table

        TableColumn<StudentRecord, String> finalCol = new TableColumn<>("Final Mark");
        finalCol.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));    // use the data name from data model
        tvStudentRecords.getColumns().add(finalCol);                             // add column to table

        TableColumn<StudentRecord, String> letterCol = new TableColumn<>("Midterm");
        letterCol.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));    // use the data name from data model
        tvStudentRecords.getColumns().add(letterCol);                             // add column to table

        TableView.TableViewSelectionModel<StudentRecord> tvSelectRecord = tvStudentRecords.getSelectionModel();

        return tvStudentRecords;
    }

    // pull all data from the TableView and save it to the current filenmae as a CSV
    // save only SID, Assignments, Midterms, Final Exam
    public static void save() {
        // read data from tableview
        TableView<StudentRecord> tvStudentRecords = getStudentRecords();

        // create new file
        File thisFile = new File(currentFilename);
        //File thisFile = new File("test.csv");

        try {
            // create a CSVWriter object
            CSVWriter csvWriter = new CSVWriter(new FileWriter(thisFile));

            // traverse the student data in the table
            for (int i = 0; i < tvStudentRecords.getItems().size(); i++) {
                StudentRecord thisStudent = tvStudentRecords.getItems().get(i);

                // get this line of student data
                String[] thisStudentRec = thisStudent.toString().split(",");

                System.out.println(thisStudent.toString());
                System.out.println();

                // write this line to csv
                csvWriter.writeNext(thisStudentRec);
            }

            // closet the CSV writer
            csvWriter.close();
        } catch (IOException e){
            e.getMessage();
        }

    }

    // load the CSV data (as above) from the current filename,
    // and populate the TableView
    public static void load(TableView<StudentRecord> tvStudentRecords) {
            
    }
    public static void main(String[] args) {
        launch();
    }
}