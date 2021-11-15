import static org.junit.jupiter.api.Assertions.*;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReveiwTest {
    private Review review = new Review(10, "review", "review", "reviewee");
    @BeforeEach

    @Test
    public void testToString() {
        String outcome = "Rating: 10" + "\nReview: review";
        assertEquals(outcome, review.toString());
    }
}
