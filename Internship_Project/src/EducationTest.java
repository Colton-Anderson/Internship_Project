import static org.junit.jupiter.api.Assertions.*;

<<<<<<< HEAD
=======

>>>>>>> 6dc870b005769ceb5c33f850af33b7d4e9f44680
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
<<<<<<< HEAD
import org.junit.jupiter.api.Test; 

import java.util.ArrayList;

public class EducationTest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private ArrayList<JobListing> wishList;
    private ArrayList<Resume> resumes;
    private ArrayList<Review> reviews;

    @BeforeEach
    public void setup() {
        return Education;
    }

    @AfterEach
    public void teardown() {
        return Education;
    }

    

    
=======
import org.junit.jupiter.api.Test;

class EducationTest {
    private Education education = new Education("School",2023,"major","minor",3.8);

    @Test
    public void testToString() {
        String outcome = education.getSchool() + "\nClass Year: " + education.getClassYear() + "\nMajor: " + education.getMajor() +
        "\nMinor: " + education.getMinor() + "\nGPA: " + education.getGpa();

        assertEquals(outcome, education.toString());
    }
>>>>>>> 6dc870b005769ceb5c33f850af33b7d4e9f44680
}
