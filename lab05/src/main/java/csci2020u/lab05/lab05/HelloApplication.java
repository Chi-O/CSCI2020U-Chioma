package csci2020u.lab05.lab05;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

import static csci2020u.lab05.lab05.DataSource.getAllMarks;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // use FlowPane layout and align table to the center of window
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);

        // create scene
        Scene scene = new Scene(root, 550, 450);

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

        // add TableView to the root layout
        root.getChildren().add(tvStudentRecords);

        stage.setTitle("Lab 05 Solution C");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}