import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DataWriterTest {
    
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();
    private ArrayList<Student> students = userList.getStudents();
    private ArrayList<Employer> employers = userList.getEmployers();
    private JobListingsList jobListings = JobListingsList.getInstance();
    private ArrayList<JobListing> listings = jobListings.getJobListings();

    @BeforeEach
    public void setup() {
        DataWriter.saveUsers(new ArrayList<User>(), new ArrayList<Employer>(), new ArrayList<Student>());
        DataWriter.saveJobListing(new ArrayList<JobListing>());
    }

    @AfterEach
    public void tearDown() {
        DataWriter.saveUsers(userList.getUsers(), userList.getEmployers(), userList.getStudents());
    }

    @Test
    public void testWritingZeroUsers() {
        DataWriter.saveUsers(new ArrayList<User>(), new ArrayList<Employer>(), new ArrayList<Student>());
        assertEquals(0, DataLoader.getUsers().size());
    }
}
