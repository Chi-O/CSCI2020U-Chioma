package lab10.lab10;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application {
    private ServerSocket serverSocket = null;
    private int numConnections;

    // constructor to make new serverSocket at the specified port
    public Server() {
        System.out.println("----- Server ------");
        try {
            serverSocket = new ServerSocket(11111);
            numConnections = 0;
        } catch (IOException ex) {
            System.out.println("Server constructor");
            ex.printStackTrace();
        }
    }

    // method to encapsulate the instructions for the server waiting for connections
    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");

            // ONLY ACCEPT TWO PLAYERS FOR NOW
            while (true) {
                Socket socket = serverSocket.accept();
                numConnections++;
                System.out.println("Client #" + numConnections + " connected");
            }
        } catch (IOException e) {
            System.out.println("Thrown from Server -> acceptConnections()");
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        acceptConnections();


        // create a GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);      // align its center
        grid.setHgap(10);                   // set horizontal and vertical gap between rows and columns
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));    // add padding

        // create the required components for the server screen
        // text area showing historical messages
        TextArea messagesArea = new TextArea();

//        // TO ADD NEW MESSAGES
//        messagesArea.setText("This just in ... ");
//        messagesArea.appendText("\nSomething special coming up ...");

        // exit button
        Button exitBtn = new Button("Exit");

        // add the components to the grid
        grid.add(messagesArea, 0, 0);

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
