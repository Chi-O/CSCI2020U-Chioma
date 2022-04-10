package tictactoe.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Player extends Application {
    private ClientSideConnection csc;

    private int myPlayerID;
    private int otherPlayerID;

    @Override
    public void start(Stage stage) throws IOException {
        connectToServer();

        BorderPane root = new BorderPane();

        Button b1 = new Button("HIT THIS BUTTON!");
        Text message = new Text();

        if (myPlayerID == 1) {
            message.setText("You are player #1. You go first");
            otherPlayerID = 2;
        } else {
            message.setText("You are player #2. Wait for your turn");
            otherPlayerID = 1;
        }

        root.setCenter(b1);
        root.setTop(message);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello! Player #" + myPlayerID);
        stage.setScene(scene);
        stage.show();

        System.out.println("You are player # " + myPlayerID);
    }

    public void connectToServer() {
        csc = new ClientSideConnection();
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
                socket = new Socket("localhost", 55555);
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());

                // read the playerID of this player; sent from the GameServer
                myPlayerID = dis.readInt();
                System.out.println("Connected to server as player #" + myPlayerID);
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