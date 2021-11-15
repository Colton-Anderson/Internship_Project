import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployerTest {

    private Employer employer;
    private UserList userList = userList.getInstance();
    private ArrayList<Employer> employerList = userList.setEmployers();

    @BeforeEach
    public void setup() {
        employerList.clear();
    }

    @AfterEach
    publci void tearDown() {
        employerList.getInstance().getUsers().clear();
    }

    @Test
    public void testEditAccount() {
        employer.editAccount("new","new","new", "new", "new");
        assertTrue()
    }

    @Test
    public void testAddListingPositive() {
        employer.addListing(new Listing("id","title","post","expire","location", 10, "ID"));
        assertTrue(employer.getCompanyListings().size() > 0);
    }

    @Test
    public void testAddListingNegative() {
        employer.addListing(new Listing("id","title","post","expire","location", -10, "ID"));
        assertTrue(employer.getCompanyListings().size() > 0);
    }

    @Test
    public void testRemoveListing() {
        Listing listing = new Listing("new", "new", "new", "new", "new", 10, "new");
        employer.addListing(listing);
        employer.removeListing(listing);
        assertTrue(employer.getCompanyListings().size() == 0);
    }

}