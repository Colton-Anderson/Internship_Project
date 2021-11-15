import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 

class StudentTest {

@BeforeEach
public void setup() {
    Student.clear();
}

@AfterEach
public void teardown() {
    Student.clear();
}

    @Test
    public void testremoveReview() {
        Review.removeReview(new review("this job is great"));
        Student.addReview(review);
        Student.removeReview(review);
        assertEquals(0, Review.getreview().size());
    }

    @Test 
    public void testaddToWishList() {
        Student.addToWishList(JobListing);
        assertTrue(Student.getWishList().size() > 1);
    }

    @Test 
    public void testRemoveFromWishList() {
        Student.addToWishList(JobListing);
        Student.removeFromWishList(JobListing);
        assertEquals(0, Student.getWishList().size());
    }

    @Test 
    public void testreviewEmployer() {
        review.addReview("It is great");
        assertTrue(review.getReview().size() > 0);
    }
} 