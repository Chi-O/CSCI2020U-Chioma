package tictactoe.tictactoe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// host the server-side functionality of the multiplayer tictactoe game
public class GameServer {
    private ServerSocket serverSocket = null;
    private int numPlayers;

    private ClientHandler player1;
    private ClientHandler player2;

    // constructor to make new serverSocket at the specified port
    public GameServer() {
        System.out.println("----- Game Server ------");
        numPlayers = 0; // initialize num players to zero
        try {
            serverSocket = new ServerSocket(55555);
        } catch (IOException ex) {
            System.out.println("Exception from GameServer constructor");
            ex.printStackTrace();
        }
    }

    // method to encapsulate the instructions for the server waiting for connections
    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");

            // ONLY ACCEPT TWO PLAYERS FOR NOW
            while (numPlayers < 2) {
                Socket socket = serverSocket.accept();
                numPlayers++;
                System.out.println("Player #" + numPlayers + " connected");

                // use a thread to handle each connection
                ClientHandler clientHandler = new ClientHandler(socket, numPlayers);
                if (numPlayers == 1) {
                    player1 = clientHandler;
                } else {
                    player2 = clientHandler;
                }
                Thread t = new Thread(clientHandler);
                t.start();
            }
            System.out.println("Max number of players connected. No longer accepting requests");
        } catch (IOException e) {
            System.out.println("GameServer; acceptConnections()");
            e.printStackTrace();
        }
    }

    // use threads to concurrently let two players play
    // use ClientHandler class to add instructions to run on each thread
    private class ClientHandler implements Runnable{
        // the client socket
        private Socket clientSocket = null;
        // input and output streams
        private DataInputStream dis = null;
        private DataOutputStream dos = null;
        private int playerID;

        // CONSTRUCTOR: receive the client socket, get input and output stream, assign the playerID
        public ClientHandler(Socket clientSocket, int playerID) {
            this.clientSocket = clientSocket;
            this.playerID = playerID;

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
            // send the player ID
            try {
                dos.writeInt(playerID);
            } catch (IOException e) {
                System.out.println("Exception from ClientHandler run()");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        GameServer gg = new GameServer();
        gg.acceptConnections();
    }
}
