/**
 * @author Carter Chandler, Lauren Hadlow
 * Holds the Characteristics of an Admin
 */

public class Admin extends User {
    /**
     * Contructor
     * @param username
     * @param password
     */
    public Admin(String username, String password) {
        super(username, password, "a");
    }

    /**
     * Constructor
     * @param username
     * @param password
     * @param uUID
     */
    public Admin(String username, String password, String uUID) {
        super(username, password, "a", uUID);
    }

    /**
     * edits the reveiw
     * @param review
     * @param editedReview
     */
    public void editReview(Review review, Review editedReview) {
        review = editedReview;
    }

    /**
     * Deletes a user
     * @param user
     */
    public void deleteUser(User user) {
        UserList.getInstance().getUsers().remove(user);
        JobListingsList listings = JobListingsList.getInstance();
        if(user.getType().equalsIgnoreCase("s")) {
            for(int i = 0; i<listings.getJobListings().size(); i++) {
                for (int j = 0; j<listings.getJobListings().get(i).getApplicants().size(); j++)
                if(listings.getJobListings().get(i).getApplicants().get(j).equals(user)) {
                    listings.getJobListings().get(i).removeApplicant(listings.getJobListings().get(i).getApplicants().get(j));
                }
            }
        }
        else if(user.getType().equalsIgnoreCase("e")) {
            for(int i = 0; i<listings.getJobListings().size(); i++) {
                listings.deleteListing(listings.getJobListings().get(i));
            }
        }
    }

    /**
     * Deletes a student reveiw
     * @param student
     * @param review
     */
    public void deleteReview(Student student, Review review) {
        if(student.getReviews().contains(review)) {
            student.removeReview(review);
        }
    }
         
    /**
     * deletes an employer reveiw
     * @param employer
     * @param review
     */
    public void deleteReview(Employer employer, Review review) {
        if(employer.getReviews().contains(review)) {
                employer.deleteReview(review);
        } 
    }
    
    /**
     * Edits a user
     * @param user
     * @param editedUser
     */
    public void editUser(User user, User editedUser) {
        user = editedUser;
    }
}
