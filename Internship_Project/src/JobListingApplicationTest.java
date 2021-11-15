import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JobListingApplicationTest {
    
    private JobListingApplication jla;
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();

    @Test
    void testCreateAccount() {
        boolean createAccount = jla.createStudentAccount("name", "password", "studentID", "firstName", "lastName", "email", "phoneNumber");
        assertTrue(createAccount);
    }

    

    
}
