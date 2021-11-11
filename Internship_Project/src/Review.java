/**
 * @author Lauren Hadlow, Colton Anderson, Mitchell Carroll
 * Instance of a Reveiw
 */
public class Review {

    private int rating;
    private String review;
    private String reviewerID;
    private String revieweeID;

    /**
     * constructor
     * @param rating
     * @param review
     * @param reviewer
     * @param reviewee
     */
    public Review(int rating, String review, String reviewer, String reviewee) {
        this.rating = rating;
        this.review = review;
        this.reviewerID = reviewer;
        this.revieweeID = reviewee;
    }

    /**
     * Returns a string with the information stored in a reveiw
     */
    public String toString() {
        return "Rating: "+rating+"\nReview: "+review;
    }

    /**
     * getters
     * @return
     */
    public String getReviewerID() {
        return this.reviewerID;
    }

    public String getRevieweeID() {
        return this.revieweeID;
    }

    public int getRating() {
        return this.rating;
    }

    public String getReview() {
        return this.review;
    }

    /**
     * adds a reveiw
     */
    public void addReview() {

    }

    /**
     * removes a reveiw
     * @param review
     */
    public void remove(Review review) {
        
    } 
}
