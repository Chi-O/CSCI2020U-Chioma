import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int cliCount = 0;

    // ClientHandler subclass to handle each client on a thread
    private static class ClientHandler implements Runnable {
        // initialize Socket object (optionally also initialize the input/output stream here)
        Socket clientSocket = null;
        BufferedReader inStream = null;

        // constructor to identity this incoming request
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        // override run() method to add functionality
        @Override
        public void run() {
            try {
                // use input stream to read from the client input stream
                inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // display the input as you read from the client
                String message;
                while ((message = inStream.readLine()) != null) {
                    System.out.println("Sent from client # " + cliCount + ": " + message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // close connection and streams
                    inStream.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // main method
    public static void main(String[] args) throws IOException {
        ServerSocket server = null;
        try {
            // make a server socket for incoming requests
            server = new ServerSocket(12345); // listen on port 12345

            server.setReuseAddress(true);
            System.out.println("Starting server...");
            System.out.println("Waiting for client connection...");

            // repeatedly accept client request, making a new thread for each of them
            while (true) {
                Socket thisClientSocket = server.accept();

                // this will display the host address of client
                cliCount++;
                System.out.println("Client #" + cliCount + " is connected " + thisClientSocket.getInetAddress().getHostAddress());

                // use ClientHandler to handle this client separately
                ClientHandler thisClient = new ClientHandler(thisClientSocket);

                // invoke the code to handle the client using new Thread(clientSocket).start()
                new Thread(thisClient).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
