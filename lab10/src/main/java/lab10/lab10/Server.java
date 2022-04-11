package lab10.lab10;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application{
    private static ServerSocket serverSocket;
    private static int numConnections;
    static TextArea messagesArea = new TextArea();

    private static boolean firstFlag = true;

    // constructor to make new serverSocket at the specified port
    public static void initServer() {
        System.out.println("----- Server ------");
        try {
            serverSocket = new ServerSocket(11111);
            numConnections = 0;

            //acceptConnections();
        } catch (IOException ex) {
            System.out.println("Thrown from Server constructor");
            ex.printStackTrace();
        }
    }

    // method to encapsulate the instructions for the server waiting for connections
    public static void acceptConnections(ServerSocket serverSocket) {
        try {
            System.out.println("Waiting for connections...");

            while (true) {
                Socket socket = serverSocket.accept();
                numConnections++;
                System.out.println("Client #" + numConnections + " connected!");

                // use a thread to handle each connection
                ClientHandler clientHandler = new ClientHandler(socket, numConnections);
                Thread t = new Thread(clientHandler);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Thrown from Server -> acceptConnections()");
            e.printStackTrace();
        }
    }

    // use threads to concurrently let two players play
    // use ClientHandler class to add instructions to run on each thread
    public static class ClientHandler implements Runnable {
        // the client socket
        private Socket clientSocket = null;
        // input and output streams
        private DataInputStream dis = null;
        private DataOutputStream dos = null;
        private final int clientID;

        // CONSTRUCTOR: receive the client socket, get input and output stream, assign the playerID
        public ClientHandler(Socket clientSocket, int clientID) {
            this.clientSocket = clientSocket;
            this.clientID = clientID;

            try {
                dis = new DataInputStream(clientSocket.getInputStream());
                dos = new DataOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                System.out.println("Exception from ClientHandler constructor");
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                // send the client ID
                dos.writeInt(clientID);

                // receive the message from client
                while (true) {
                    String message = dis.readUTF();
                    if (firstFlag) {
                        messagesArea.appendText(message);
                        firstFlag = false;
                    } else {
                        messagesArea.appendText("\n\n" + message);
                    }
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Exception from ClientHandler run()");
                e.printStackTrace();
            }
        }
    }

    // that you write another thread class to handle the listening of incoming connections
    public static class Connection extends Thread {
        @Override
        public void run() {
            initServer();
            acceptConnections(serverSocket);
        }
    }

    @Override
    public void start(Stage stage) {
        // create a GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);      // align its center
        grid.setHgap(10);                   // set horizontal and vertical gap between rows and columns
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));    // add padding

        // exit button
        Button exitBtn = new Button("Exit");

//        // EVENT HANDLER FOR EXIT BUTTON
//        exitBtn.setOnAction(event -> {
//            // close connection and streams
//            closeFlag = false;
//            closeConnection();
//        });

        // add the components to the grid
        grid.add(messagesArea, 0, 0);

        grid.add(exitBtn, 0, 1);

        // create a Scene node and add the GridPane grid node to the scene
        Scene scene = new Scene(grid, 320, 240);
        stage.setTitle("Simple Server v1.0");
        stage.setScene(scene);
        stage.show();

        // acceptConnections();

        // stage.close();
    }

    public static void main(String[] args) {
        // Connection conn = new Connection(new Server());
        Connection conn = new Connection();
        conn.start();

        launch();
    }
}
