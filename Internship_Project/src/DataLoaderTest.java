import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DataLoaderTest {

    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Employer> employers = new ArrayList<Employer>();
    private JobListingsList listingsList = JobListingsList.getInstance();
    private ArrayList<JobListing> listings = new ArrayList<JobListing>();

    @BeforeEach 
    public void setup() {
        users.clear();
        JobListing listing = new JobListing("y003245", "title", "postedDate", "expirationDate", "location", 9, "i839944");
        listings.add(listing);
        Student student = new Student("username", "password", "y889923", "firstName", "lastName", "email", "phoneNumber");
        Employer employer = new Employer("i839944", "username2", "password", "aName", "aDescription", "aLocation");
        users.add(student);
        students.add(student);
        users.add(employer);
        employers.add(employer);
        DataWriter.saveUsers(users, employers, students);
        DataWriter.saveJobListing(listings);
    }

    @AfterEach
    public void tearDown() {
        DataWriter.saveUsers(userList.getUsers(), userList.getEmployers(), userList.getStudents());
        DataWriter.saveJobListing(listingsList.getJobListings());
    }

    @Test
    public void testGetUsersSize() {
        users = DataLoader.getUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void testGetStudentsSize() {
        students = DataLoader.getStudents();
        assertEquals(1, students.size());
    }

    @Test
    public void testGetEmployersSize() {
        employers = DataLoader.getEmployers();
        assertEquals(1, employers.size());
    }

}
