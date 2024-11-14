package code.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.BufferedReader;

public class Server {
    public static void main(String[] args) {
        try {
            // Create server socket to listen on port 1234
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server is listening on port 1234...");

            // Accept client connections
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            // Set up input and output streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read message from the client
            String message = in.readLine();
            System.out.println("Received from client: " + message);

            // Send a response to the client
            out.println("Hello, client! Your message was: " + message);

            // Close the connections
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
