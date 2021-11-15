import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployerTest {

    private Employer employer;
    private UserList userList = UserList.getInstance();
    private ArrayList<Employer> employerList = userList.getEmployers();

    @BeforeEach
    public void setup() {
        employerList.clear();
    }

    @AfterEach
    public void tearDown() {
        //employerList.getInstance().getUsers().clear();
    }

    @Test
    public void testEditAccount() {
        employer.editAccount("new","new","new", "new", "new", 10);
        assertEquals("new",employer.getCompanyName());
    }

    @Test
    public void testAddListingPositive() {
        employer.addListing(new JobListing("id","title","post","expire","location", 10, "ID"));
        assertTrue(employer.getCompanyListings().size() > 0);
    }

    @Test
    public void testAddListingNegative() {
        employer.addListing(new JobListing("id","title","post","expire","location", -10, "ID"));
        assertTrue(employer.getCompanyListings().size() > 0);
    }

    @Test
    public void testRemoveListing() {
        JobListing listing = new JobListing("new", "new", "new", "new", "new", 10, "new");
        employer.addListing(listing);
        employer.removeListing(listing);
        assertEquals(0, employer.getCompanyListings().size());
    }

}