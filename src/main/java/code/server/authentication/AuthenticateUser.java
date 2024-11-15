package code.server.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class AuthenticateUser {

    /**
     * Path to the users.json file containing user data.
     */
    private static final String USER_DATA_FILE = "src/main/java/code/server/authentication/users.json";

    /**
     * Authenticates a user by checking the provided username and password against the stored user data.
     *
     * @param username the username to authenticate
     * @param password the password to authenticate
     * @return true if the credentials match a stored user, false otherwise
     */
    public boolean authenticate(String username, String password) {
        JsonNode usersNode = readUsersFromJson();

        if (usersNode == null) {
            return false;
        }

        for (JsonNode userNode : usersNode) {
            String storedUsername = userNode.get("username").asText();
            String storedPassword = userNode.get("password").asText();

            if (storedUsername.equals(username) && storedPassword.equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reads user data from the JSON file and extracts the 'users' array.
     *
     * @return a JsonNode representing the 'users' array, or null if an error occurs
     */
    private JsonNode readUsersFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(new File(USER_DATA_FILE));
            return rootNode.get("users");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
