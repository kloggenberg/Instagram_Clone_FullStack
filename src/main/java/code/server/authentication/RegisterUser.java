package code.server.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class RegisterUser {

    private final String userDataFile;

    // Constructor to allow custom file paths for testing
    public RegisterUser(String userDataFile) {
        this.userDataFile = userDataFile;
    }

    /**
     * Registers a new user by adding their username and password to the users.json file.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return true if the user was successfully added, false if an error occurred
     */
    public boolean register(String username, String password) {
        // Reject invalid or missing username/password
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return false;
        }

        ObjectMapper mapper = new ObjectMapper();
        File file = new File(userDataFile);

        try {
            JsonNode rootNode;
            ArrayNode usersNode;

            // Ensure the file exists and has the expected structure
            if (file.exists()) {
                rootNode = mapper.readTree(file);
                usersNode = (ArrayNode) rootNode.get("users");
                if (usersNode == null) {
                    throw new IOException("Malformed JSON: 'users' node is missing.");
                }
            } else {
                // Create a new JSON structure if the file does not exist
                rootNode = mapper.createObjectNode();
                usersNode = mapper.createArrayNode();
                ((ObjectNode) rootNode).set("users", usersNode);
            }

            // Check if the username already exists
            for (JsonNode userNode : usersNode) {
                if (userNode.get("username").asText().equals(username)) {
                    return false; // Username already exists
                }
            }

            // Add the new user
            ObjectNode newUser = mapper.createObjectNode();
            newUser.put("username", username);
            newUser.put("password", password);
            usersNode.add(newUser);

            // Write back to the file
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
