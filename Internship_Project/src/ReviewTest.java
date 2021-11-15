import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 


public class ReviewTest {
    private int rating;
    private String review;
    private String reviewerID;
    private String revieweeID;

    @BeforeEach
    public void setup() {
        review = new review("I loved this job")
    }

    @Test 
    public void testaddReview() {
        review.addReview("It is great");
        assertTrue(review.getReview().size() > 0);
    }

    @Test
    public void testremove() {
        review.addReview("It is great");
        review.remove(review);
        assertEqual(0, review.getReview().size());
    }
    
}
