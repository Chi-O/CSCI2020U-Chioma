// A Java program for a Client

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    // initialize socket and input output streams
    private Socket clientSocket;
    private DataOutputStream out;
    private Scanner scanner;

    // constructor to create a socket with given IP and port address. i.e. start connection
    public Client(String address, int port) throws IOException {
        // establish a connection
        clientSocket = new Socket(address, port);
        System.out.println("Connected to server...");
        System.out.println("Input \"done\" to kill connection...");

        // taking input
        scanner = new Scanner(System.in);

        // open output stream on the socket
        out = new DataOutputStream(clientSocket.getOutputStream());

        String message = "";
        int num = 0;

        // read until "done" is input
        while (!message.equals("done")) {
            message = scanner.nextLine();
            num = scanner.nextInt();
            try {
                out.writeUTF(message); // send output from client to server
                out.writeInt(num);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }


//        // string to read message from input
//        String line = "";
//
//        // keep reading until "Over" is input
//        while (!line.equals("Over")) {
//            line = input.nextLine();
//            out.println(line);
//        }

        // for closing the connection
        out.close();
        clientSocket.close();

    }


    public static void main(String[] args) throws IOException {
        Client clientServer = new Client("localhost", 6666);
    }
}
