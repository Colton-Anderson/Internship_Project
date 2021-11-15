import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class JobListingsListTest {

    JobListingsList jobList = JobListingsList.getInstance();
    ArrayList<JobListing> listings = jobList.getJobListings();

    @BeforeEach
    public void setup() {
        jobList.getJobListings().clear();
        jobList.addListing(new JobListing("h734949","hfgl","swhf","hfkh","skhgf",9,"n616165"));
    }

    @AfterEach
    public void tearDown() {
        JobListingsList.getInstance().getJobListings().clear();
        DataWriter.saveJobListing(JobListingsList.getInstance().getJobListings());
    }
    
    @Test 
    public void testGetInstanceNull() {
        assertNotNull(JobListingsList.getInstance());
    }

    @Test 
    public void testGetInstanceSecond() {
        JobListingsList.getInstance();
        assertNotNull(JobListingsList.getInstance());
    }

    @Test
    public void testConstructorWithList() {
        JobListingsList jobList = JobListingsList.getInstance();
        assertNotNull(jobList);
    }

    @Test
    public void testConstructorWithoutList() {
        DataWriter.saveJobListing(new ArrayList<JobListing>());
        assertEquals(0, JobListingsList.getInstance().getJobListings().size());
    }

    @Test
    public void testSetEmployers() {
        ArrayList<Employer> employers = new ArrayList<Employer>();
        employers.add(new Employer("n616165", "amazon", "password", "amazon", "fun", "location"));
        jobList.setEmployers(employers);
        assertEquals("n616165", jobList.getJobListings().get(0).getEmployerID());
    }

    @Test
    public void testGetListings() {
        assertTrue(jobList.getJobListings().size()>0);
    }

    @Test 
    public void testAddListing() {
        jobList.getJobListings().clear();
        jobList.addListing(new JobListing("h734949","hfgl","swhf","hfkh","skhgf",9,"n616165"));
        assertTrue(jobList.getJobListings().size()>0);
    }

    @Test 
    public void testUpdateListing() {
        jobList.getJobListings().clear();
        jobList.addListing(new JobListing("h734949","hfgl","swhf","hfkh","skhgf",9,"n616165"));
        jobList.updateListing(new JobListing("h734949","hfml","swhf","hfkh","skhgf",9,"n616165"));
        assertTrue(jobList.getJobListings().get(0).getTitle() == "hfml");
    }

    @Test
    public void testUpdateListingDoesntExist() {
        jobList.getJobListings().clear();
        jobList.addListing(new JobListing("h734949","hfgl","swhf","hfkh","skhgf",9,"n616165"));
        jobList.updateListing(new JobListing("djfhgk","hfml","swhf","hfkh","skhgf",9,"n616165"));
        assertTrue(jobList.getJobListings().get(0).getTitle() == "hfgl");
    }

    @Test
    public void testSearchListings() {
        jobList.getJobListings().clear();
        jobList.addListing(new JobListing("h734949","hfgl","swhf","hfkh","skhgf",9,"n616165"));
        assertEquals(jobList.searchListings("f").size(), 1);
    }

    @Test 
    public void testContainsTrue() {
        jobList.getJobListings().clear();
        JobListing listing = new JobListing("h734949","hfgl","swhf","hfkh","skhgf",9,"n616165");
        jobList.addListing(listing);
        assertTrue(jobList.contains(listing));
    }

    @Test 
    public void testContainsFalse() {
        jobList.getJobListings().clear();
        JobListing listing = new JobListing("h734949","hfgl","swhf","hfkh","skhgf",9,"n616165");
        assertFalse(jobList.contains(listing));
    }

    @Test
    public void testDeleteListing() {
        jobList.getJobListings().clear();
        JobListing listing = new JobListing("h734949","hfgl","swhf","hfkh","skhgf",9,"n616165");
        jobList.addListing(listing);
        jobList.deleteListing(listing);
        assertEquals(0, jobList.getJobListings().size());
    }
}
