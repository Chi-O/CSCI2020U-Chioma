package lab10.lab10;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // create a GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);      // align its center
        grid.setHgap(10);                   // set horizontal and vertical gap between rows and columns
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));    // add padding

        // create the required components for the client screen
        // Username (a string)
        Label userName = new Label("Username: ");
        TextField userNameText = new TextField();

        // Password (a string)
        Label msg = new Label("Message: ");
        TextField msgText = new TextField();

        // send button
        Button sendBtn = new Button("Send");

        // exit button
        Button exitBtn = new Button("Exit");

        // add the components to the grid
        grid.add(userName, 0, 0);
        grid.add(userNameText, 1, 0);

        grid.add(msg, 0, 1);
        grid.add(msgText, 1, 1);

        grid.add(sendBtn, 0, 2);

        grid.add(exitBtn, 0, 3);

        // create a Scene node and add the GridPane grid node to the scene
        Scene scene = new Scene(grid, 320, 240);
        stage.setTitle("Simple Client v1.0");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}