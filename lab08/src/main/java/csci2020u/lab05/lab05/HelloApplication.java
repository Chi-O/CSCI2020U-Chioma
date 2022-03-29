package csci2020u.lab05.lab05;

import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
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
    public void start(Stage stage) throws IOException, CsvValidationException {
        // get student records table view
        // get student marks
        ObservableList<StudentRecord> marks = getAllMarks();
        TableView<StudentRecord> tvStudentRecords = getStudentRecords(marks);


        // create File menu instance
        Menu fileMenu = new Menu("File");

        // items to File menu
        MenuItem fileNew = new MenuItem("New");
        MenuItem fileOpen = new MenuItem("Open");
        MenuItem fileSave = new MenuItem("Save");
        MenuItem fileSaveAs = new MenuItem("Save As");
        MenuItem fileExit = new MenuItem("Exit");

        fileMenu.getItems().add(fileNew);
        fileMenu.getItems().add(fileOpen);
        fileMenu.getItems().add(fileSave);
        fileMenu.getItems().add(fileSaveAs);
        fileMenu.getItems().add(fileExit);

        // create a MenuBar instance and add File menu
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        ///TableView<StudentRecord> tvStudentRecords = load();
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

        // EVENT HANDLERS
        // when "New" is clicked, clear all items in the TableView
        fileNew.setOnAction(event -> {
            tvStudentRecords.getItems().clear();
        });

        // when "Open" is clicked, load and display csv
        fileOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // show a file chooser
                // update the current filename to the user selected filename
                currentFilename = getCurrentFilename(stage);

                // call load()
                try {
                    TableView<StudentRecord> newStudentRecords = load();
                    root.setCenter(newStudentRecords);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CsvValidationException e) {
                    e.printStackTrace();
                }
            }
        });

        // when "Save" is clicked save the current table view
        fileSave.setOnAction(event -> {
            save((TableView<StudentRecord>) root.getCenter());
        });

        // when "Save As" is clicked, open file chooser and save the current table view
        fileSaveAs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // show a file chooser
                // update the current filename to the user selected filename
                FileChooser fileChooser = new FileChooser();
                currentFilename = fileChooser.showSaveDialog(stage).getName();

                // call load()
                save((TableView<StudentRecord>) root.getCenter());
            }
        });

        // when "Exit" is clicked, exit the application
        fileExit.setOnAction(event -> {
            stage.close();
        });
    }

    // returns a tableview using data from the marks list
    public static TableView<StudentRecord> getStudentRecords(ObservableList<StudentRecord> marks) {
//        // get student marks
//        ObservableList<StudentRecord> marks = getAllMarks();

        // create a TableView object
        TableView<StudentRecord> studentRecords;
        studentRecords = new TableView<StudentRecord>(marks);

        // associate the columns with column headings
        // use cell factory to populate cells with data
        TableColumn<StudentRecord, String> studentIDColumn = new TableColumn<>("SID");
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));    // use the data name from data model
        studentRecords.getColumns().add(studentIDColumn);                             // add column to table

        TableColumn<StudentRecord, String> assignmentsColumn = new TableColumn<>("Assignments");
        assignmentsColumn.setCellValueFactory(new PropertyValueFactory<>("assignmentsGrade"));    // use the data name from data model
        studentRecords.getColumns().add(assignmentsColumn);                             // add column to table

        TableColumn<StudentRecord, String> midtermCol = new TableColumn<>("Midterm");
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("midtermGrade"));    // use the data name from data model
        studentRecords.getColumns().add(midtermCol);                             // add column to table

        TableColumn<StudentRecord, String> examCol = new TableColumn<>("Final Exam");
        examCol.setCellValueFactory(new PropertyValueFactory<>("examGrade"));    // use the data name from data model
        studentRecords.getColumns().add(examCol);                             // add column to table

        TableColumn<StudentRecord, String> finalCol = new TableColumn<>("Final Mark");
        finalCol.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));    // use the data name from data model
        studentRecords.getColumns().add(finalCol);                             // add column to table

        TableColumn<StudentRecord, String> letterCol = new TableColumn<>("Midterm");
        letterCol.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));    // use the data name from data model
        studentRecords.getColumns().add(letterCol);                             // add column to table

        TableView.TableViewSelectionModel<StudentRecord> tvSelectRecord = studentRecords.getSelectionModel();

        return studentRecords;
    }

    // pull all data from the TableView and save it to the current filename as a CSV
    // save only SID, Assignments, Midterms, Final Exam
    public static void save(TableView<StudentRecord> currentTableView) {
        // get student marks
        // TODO: this should read from the current TableView
//        ObservableList<StudentRecord> marks = getAllMarks();
//
//        // read data from tableview
//        TableView<StudentRecord> tvStudentRecords = getStudentRecords(marks);

        // create new file
        File thisFile = new File(currentFilename);
        //File thisFile = new File("test.csv");

        try {
            // create a CSVWriter object
            CSVWriter csvWriter = new CSVWriter(new FileWriter(thisFile));

            // traverse the student data in the table
            for (int i = 0; i < currentTableView.getItems().size(); i++) {
                StudentRecord thisStudent = currentTableView.getItems().get(i);

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
    public static TableView<StudentRecord> load() throws IOException, CsvValidationException {
        // new CSV readers instance
        CSVReader csvReader = new CSVReader(new FileReader(currentFilename));
        // CSVReader csvReader = new CSVReader(new FileReader("test.csv")); // FOR DEBUGGING PURPOSES
        String[] nextRecord;

        // DATA SOURCE
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();

        // Student ID, Assignments, Midterm, Final exam
        // read the csv line by line
        while((nextRecord = csvReader.readNext()) != null) {
            marks.add(new StudentRecord(nextRecord[0], Float.parseFloat(nextRecord[1]), Float.parseFloat(nextRecord[2]), Float.parseFloat(nextRecord[3])));
        }

        // FOR DEBUGGING PURPOSES TO CHECK IF DATA WAS READ CORRECTLY
//        for (StudentRecord mark : marks) {
//            System.out.println(mark.toString());
//        }

        return getStudentRecords(marks);
    }

    // open file chooser and get selected filename
    // returns the file name
    public static String getCurrentFilename(Stage stage) {
        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(stage);

        return selectedFile.getName();
    }


    public static void main(String[] args) {
        launch();
    }
}