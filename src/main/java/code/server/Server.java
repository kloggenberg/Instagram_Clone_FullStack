package code.server;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try {
            // Create server socket to listen on port 1234
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is listening on port 5000...");

            while (true) {
                // Accept client connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                // Create a new ClientHandler thread to handle the communication with the client
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}