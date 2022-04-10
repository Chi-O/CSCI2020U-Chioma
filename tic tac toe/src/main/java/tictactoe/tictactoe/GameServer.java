package tictactoe.tictactoe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// host the server-side functionality of the multiplayer tictactoe game
public class GameServer {
    private ServerSocket serverSocket = null;
    private int numPlayers;

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
            }
            System.out.println("Max number of players connected. No longer accepting requests");
        } catch (IOException e) {
            System.out.println("GameServer; acceptConnections()");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GameServer gg = new GameServer();
        gg.acceptConnections();
    }
}
