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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Application {
    private ClientSideConnection csc;
    private int myClientID;

    @Override
    public void start(Stage stage) throws IOException {
        connectToServer();

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

        // EVENT HANDLER FOR SEND BUTTON
        sendBtn.setOnAction(event -> {
            try {
                sendToServer(userNameText.getText(), msgText.getText());
            } catch (IOException e) {
                System.out.println("From send button event handler.");
                e.printStackTrace();
            }
        });

        // EVENT HANDLER FOR EXIT BUTTON
        exitBtn.setOnAction(event -> {
            // close connection and streams
            closeConnection();
        });

        // add the components to the grid
        grid.add(userName, 0, 0);
        grid.add(userNameText, 1, 0);

        grid.add(msg, 0, 1);
        grid.add(msgText, 1, 1);

        grid.add(sendBtn, 0, 2);

        grid.add(exitBtn, 0, 3);

        // create a Scene node and add the GridPane grid node to the scene
        Scene scene = new Scene(grid, 320, 240);
        stage.setTitle("Simple Client #" + myClientID);
        stage.setScene(scene);
        stage.show();
    }

    private void connectToServer() {
        csc = new ClientSideConnection();
    }

    private void sendToServer(String userName, String message) throws IOException {
        try {
            csc.dos.writeUTF(userName + ": " + message);
        } catch (IOException ex) {
            System.out.println("Thrown from sendToServer()");
            ex.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            // close connection and streams
            csc.dos.flush();
            csc.dis.close();
            csc.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // inner class to encapsulate instructions to allow player communicate with the GameServer
    private class ClientSideConnection {
        private Socket socket = null;
        private DataInputStream dis = null;
        private DataOutputStream dos = null;

        // constructor
        public ClientSideConnection() {
            System.out.println("----- Client -----");
            try {
                socket = new Socket("localhost", 11111);
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());

                // read the playerID of this player; sent from the GameServer
                myClientID = dis.readInt();
                System.out.println("Connected to server as player #" + myClientID);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("From CSC constructor");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}