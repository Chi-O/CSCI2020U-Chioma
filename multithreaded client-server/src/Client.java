import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        // establish a connection using new Socket
        try (Socket client = new Socket("localhost", 12345)) {
            System.out.println("Connected to server...");
            System.out.println("Input \"done\" to kill connection...");

            // output stream for communication
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            // scanner for reaching input from the console (System.in)
            Scanner scanner = new Scanner(System.in);

            // send user input as a message using the output stream
            String msg = "";

            while (!msg.equals("done")) {
                msg = scanner.nextLine(); // read the next line of input
                out.println(msg); // send the message using the output stream
            }

            // close scanner
            scanner.close();
        } catch (IOException e) { // exception handling
            e.printStackTrace();
        }
        // PrintWriter auto flush is turned on so the output stream terminates
        System.out.println("Connection termincated...");
    }
}
