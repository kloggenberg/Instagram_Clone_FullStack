package code.authentication;

import code.server.authentication.AuthenticateUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticateUserTest {

    @Test
    void testAuthenticateValidUser() {
        AuthenticateUser authUser = new AuthenticateUser();

        // Valid credentials
        assertTrue(authUser.authenticate("user1", "password1"), "User1 should authenticate successfully");
        assertTrue(authUser.authenticate("user2", "password2"), "User2 should authenticate successfully");
        assertTrue(authUser.authenticate("user3", "password3"), "User3 should authenticate successfully");
    }

    @Test
    void testAuthenticateInvalidUser() {
        AuthenticateUser authUser = new AuthenticateUser();

        // Invalid username
        assertFalse(authUser.authenticate("user4", "password1"), "Nonexistent username should fail authentication");

        // Invalid password
        assertFalse(authUser.authenticate("user1", "wrongpassword"), "Incorrect password should fail authentication");

        // Both username and password invalid
        assertFalse(authUser.authenticate("nonexistent", "invalid"), "Invalid username and password should fail authentication");
    }

    @Test
    void testAuthenticateEmptyCredentials() {
        AuthenticateUser authUser = new AuthenticateUser();

        // Empty username and password
        assertFalse(authUser.authenticate("", ""), "Empty username and password should fail authentication");

        // Empty username
        assertFalse(authUser.authenticate("", "password1"), "Empty username should fail authentication");

        // Empty password
        assertFalse(authUser.authenticate("user1", ""), "Empty password should fail authentication");
    }

    @Test
    void testAuthenticateWithNullValues() {
        AuthenticateUser authUser = new AuthenticateUser();

        // Null username and password
        assertFalse(authUser.authenticate(null, null), "Null username and password should fail authentication");

        // Null username
        assertFalse(authUser.authenticate(null, "password1"), "Null username should fail authentication");

        // Null password
        assertFalse(authUser.authenticate("user1", null), "Null password should fail authentication");
    }
}
