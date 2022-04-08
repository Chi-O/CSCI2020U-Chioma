import java.io.*;
import java.net.*;

public class Server {

    //Initialize input stream and socket
    private DataInputStream inStream = null;
    private Socket sock = null;
    private ServerSocket serve = null;

    public Server(int port) {
        //start server and wait for client
        try {
            serve = new ServerSocket(port);
            System.out.println("Starting Server...");
            System.out.println("Waiting for client connection...");
            sock = serve.accept();
            System.out.println("Connected to Client...");
            inStream = new DataInputStream(sock.getInputStream());

            //holds message sent by client
            String message = "";
            int num;

            while (!message.equals("done")) {
                try {
                    message = inStream.readUTF(); // receive input from client
                    num = inStream.readInt();
                    System.out.println("message: " + message);
                    System.out.println("num: " + num);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
            sock.close();
            inStream.close();
            System.out.println("Closed connection");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        Server server = new Server(6666);
    }

}