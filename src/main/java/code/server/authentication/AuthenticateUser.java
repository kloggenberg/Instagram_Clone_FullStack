package code.server.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class AuthenticateUser {

    // Path to the users.json file
    private static final String USER_DATA_FILE = "path/to/users.json"; // Update this path

    // Method to authenticate a user
    public boolean authenticate(String username, String password) {
        // Read the users data from JSON file
        JsonNode usersNode = readUsersFromJson();

        if (usersNode == null) {
            return false;
        }

        // Loop through the users and check if any match the username and password
        for (JsonNode userNode : usersNode) {
            String storedUsername = userNode.get("username").asText();
            String storedPassword = userNode.get("password").asText();

            if (storedUsername.equals(username) && storedPassword.equals(password)) {
                return true; // Authentication successful
            }
        }
        return false; // Authentication failed
    }

    // Helper method to read users from the JSON file
    private JsonNode readUsersFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Parse the JSON file
            JsonNode rootNode = mapper.readTree(new File(USER_DATA_FILE));
            return rootNode.get("users"); // Extract the 'users' array
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if there's an error reading the file
        }
    }
}
