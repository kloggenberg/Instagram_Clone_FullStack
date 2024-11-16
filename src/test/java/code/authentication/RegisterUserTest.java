package code.server.authentication;

import org.junit.jupiter.api.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;

class RegisterUserTest {

    private static final String TEST_FILE = "src/test/resources/test_users.json";
    private RegisterUser registerUser;

    @BeforeEach
    void setUp() throws IOException {
        // Create a test file with initial user data
        File testFile = new File(TEST_FILE);
        testFile.getParentFile().mkdirs();

        String initialContent = """
        {
          "users" : [
            { "username" : "user1", "password" : "password1" },
            { "username" : "user2", "password" : "password2" }
          ]
        }
        """;

        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(initialContent);
        }

        // Use the test file in RegisterUser
        registerUser = new RegisterUser(TEST_FILE);
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up the test file
        Files.deleteIfExists(new File(TEST_FILE).toPath());
    }

    @Test
    void testRegisterNewUser() {
        assertTrue(registerUser.register("newUser", "password123"));
    }

    @Test
    void testRegisterExistingUser() {
        assertFalse(registerUser.register("user1", "newPassword")); // User already exists
    }

    @Test
    void testRegisterUserWithoutPassword() {
        assertFalse(registerUser.register("userWithoutPassword", "")); // No password provided
        assertFalse(registerUser.register("userWithoutPassword", null)); // Null password
    }

    @Test
    void testRegisterUserWithoutUsername() {
        assertFalse(registerUser.register("", "password123")); // No username provided
        assertFalse(registerUser.register(null, "password123")); // Null username
    }
}
