package code.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            // Set up input and output streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read message from the client
            String message = in.readLine();
            System.out.println("Received from client: " + message);

            // Send a response to the client
            out.println("Hello, client! Your message was: " + message);

            // Close the connection
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error in ClientHandler: " + e.getMessage());
        }
    }

}
