import static org.junit.jupiter.api.Assertions.*;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsetTest {
    private User user = new User("username","password","student");

    @Test
    public void testVerifyUsername() {
        String username = "username";
        assertTrue(user.verifyUsername(username));

    }

    @Test
    public void testVerifyPassword() {
        String password = "password";
        assertTrue(user.verifyPassowrd(password));
    }

    @Test
    public void testCreateUUID() {
        String uuid = user.createUUID();
        assertTrue(uuid != null);
    }

    @Test
    public void testToString() {
        User user1 = new User("nusername", "pass", "123456");
        assertEquals("Username nusername" + "\nPassword pass" + "\nuUID 123456", user1.toString());
    }
}
