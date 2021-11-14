import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class JobListingTest {
    
    JobListing listing;

    @BeforeEach
    public void setup() {
        listing = new JobListing("h734949","hfgl","swhf","hfkh","skhgf",9,"n616165");
    }

    @Test
    public void testApply() {
        listing.apply(new Student("mitch","passwrod","y883940","mitch","car","mitch@eail","39759"));
        assertTrue(listing.getApplicants().size()>0);
    }

    @Test 
    public void testRemoveApplicant() {
        Student student = new Student("mitch","passwrod","y883940","mitch","car","mitch@eail","39759");
        listing.apply(student);
        listing.removeApplicant(student);
        assertTrue(listing.getApplicants().size()==0);
    }

    @Test 
    public void testAddDesiredSkill() {
        listing.addDesiredSkill("skill");
        assertEquals(1, listing.getDesiredSkills().size());
    }
    
}
