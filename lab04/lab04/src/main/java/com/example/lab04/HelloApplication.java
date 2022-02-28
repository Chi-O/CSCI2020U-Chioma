package com.example.lab04;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.LightBase;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // create a GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);      // align its center
        grid.setHgap(10);                   // set horizontal and vertical gap between rows and columns
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));    // add padding

        // create a Scene node and add the GridPane grid node to the scene
        Scene scene = new Scene(grid, 350, 375);    // set window to 300 by 275

        // create the required components for the registration screen
        // Username (a string)
        Label userName = new Label("Username: ");
        TextField userNameText = new TextField();

        // Password (a string)
        Label pw = new Label("Password: ");
        PasswordField pwBox = new PasswordField();

        // Full name (a string)
        Label fullName = new Label("Full Name: ");
        TextField fullNameText = new TextField();

        // E-Mail address (a string)
        Label eMail = new Label("E-Mail: ");
        TextField eMailText = new TextField();

        // Phone number (a string, format: 000-000-0000)
        Label phone = new Label("Phone #: ");
        TextField phoneNum = new TextField();

        // Date of birth (a date)
        Label dob = new Label("Date of Birth");
        DatePicker dobDate = new DatePicker();

        // Register button
        // TODO add onCLick event handler to print the field values to the console
        Button registerBtn = new Button("Register");

        registerBtn.setOnAction(event -> System.out.println("Username: " + userNameText.getText()
                        + "\nPassword: " + pwBox.getText()
                        + "\nFull name: " + fullNameText.getText()
                        + "\nPhone #: " + phoneNum.getText()
                        + "\nE-Mail: " + eMailText.getText()
                        + "\nDate of Birth: " + dobDate.getValue()
        ));
        
        // add the components to the grid
        grid.add(userName, 0, 0);
        grid.add(userNameText, 1, 0);

        grid.add(pw, 0, 1);
        grid.add(pwBox, 1, 1);

        grid.add(fullName, 0, 2);
        grid.add(fullNameText, 1, 2);

        grid.add(eMail, 0, 3);
        grid.add(eMailText, 1, 3);

        grid.add(phone, 0, 4);
        grid.add(phoneNum, 1, 4);

        grid.add(dob, 0,5);
        grid.add(dobDate, 1, 5);

        grid.add(registerBtn, 1, 6);

        // grid.setGridLinesVisible(true);  // toggle grid lines

        stage.setTitle("Lab 04 Solution C");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}