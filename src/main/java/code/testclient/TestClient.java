package code.testclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) {
        try {
            // Connect to the server on localhost and port 1234
            Socket socket = new Socket("localhost", 5000);

            // Set up input and output streams for communication
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read user input and send it to the server
            System.out.print("Enter a message for the server: ");
            String message = userInput.readLine();
            out.println(message);

            // Read and print the response from the server
            String serverResponse = in.readLine();
            System.out.println("Server response: " + serverResponse);

            // Close the connection
            socket.close();
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}