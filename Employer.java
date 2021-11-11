import java.util.ArrayList;

/**
 * @author Carter Chandler
 * A class which stores information for employers
 */
public class Employer extends User{

    private String companyName;
    private String companyDescription;
    private String companyLocation;
    private int companyRating;
    private ArrayList<JobListing> companyListings;
    private ArrayList<Review> reviews;

    /**
     * A constructor method
     * @param username The username for the employer
     * @param password The password for the employer
     * @param aName The name of the employer
     * @param aDescription The description of the employer
     * @param aLocation The location of the employer
     */
    public Employer(String username, String password, String aName, String aDescription, String aLocation) {
        super(username, password, "e");
        this.companyName = aName;
        this.companyDescription = aDescription;
        this.companyLocation = aLocation;
        this.companyRating = 0;
        this.companyListings = new ArrayList<JobListing>();
        this.reviews = new ArrayList<Review>();
    }

    /**
     * A constructor
     * @param uUID The uuid for the employer
     * @param username The username for the employer
     * @param password The password for the employer
     * @param aName The name of the employer
     * @param aDescription The description of the employer
     * @param aLocation The location of the employer
     */
    public Employer(String uUID, String username, String password, String aName, String aDescription, String aLocation) {
        super(username, password, "e", uUID);
        this.companyName = aName;
        this.companyDescription = aDescription;
        this.companyLocation = aLocation;
        this.companyRating = 0;
        this.companyListings = new ArrayList<JobListing>();
        this.reviews = new ArrayList<Review>();
    }

    /**
     * A constructor
     * @param uUID The uuid for the employer
     * @param username The username for the employer
     * @param password The password for the employer
     * @param aName The name of the employer
     * @param aDescription The description of the employer
     * @param aLocation The location of the employer
     * @param aRating The rating of the employer
     */
    public Employer(String uUID, String username, String password, String aName, String aDescription, String aLocation, int aRating) {
        super(username, password, "e", uUID);
        this.companyName = aName;
        this.companyDescription = aDescription;
        this.companyLocation = aLocation;
        this.companyRating = aRating;
        this.companyListings = new ArrayList<JobListing>();
        this.reviews = new ArrayList<Review>();
    }

    /*public Employer(String uUID, String username, String password, String aName, String aDescription, String aLocation, int aRating, ArrayList<JobListing> aListings, ArrayList<Review> aReviews) {
        super(username, password, "e", uUID);
        this.companyName = aName;
        this.companyDescription = aDescription;
        this.companyLocation = aLocation;
        this.companyRating = aRating;
        this.companyListings = aListings;
        this.reviews = aReviews;
    }*/

    /**
     * A getter method
     * @return The company Name
     */
    public String getCompanyName() {
        return this.companyName;
    }
    /**
     * A getter method
     * @return The company description
     */
    public String getCompanyDescription() {
        return this.companyDescription;
    }
    /**
     * A getter method
     * @return The company location
     */
    public String getCompanyLocation() {
        return this.companyLocation;
    }

    /**
     * A getter method
     * @return The company rating
     */
    public int getCompanyRating() {
        return this.companyRating;
    }

    /**
     * A getter method
     * @return The company listings
     */
    public ArrayList<JobListing> getCompanyListings() {
        return this.companyListings;
    }

    /**
     * A getter method
     * @return The reviews
     */
    public ArrayList<Review> getReviews() {
        return this.reviews;
    }

    /**
     * A method which edits the account
     * @param username The new username
     * @param password The new password
     * @param aName The new name
     * @param aDescription THe new description
     * @param aLocation The new location
     * @param aRating The new rating
     */
    public void editAccount(String username, String password, String aName, String aDescription, String aLocation, int aRating) {
        if(username.length()>0) {
            super.username = username;
        }
        if(password.length()>0) {
            super.password = password;
        }
        if(aName.length()>0) {
            this.companyName = aName;
        }
        if(aDescription.length()>0) {
            this.companyDescription = aDescription;
        }
        if(aLocation.length()>0) {
            this.companyLocation = aLocation;
        }
    }

    /**
     * A method which adds a listing
     * @param listing THe listing to add
     */
    public void addListing(JobListing listing) {
        listing.setEmployer(this);
        if(!companyListings.contains(listing)) {
            companyListings.add(listing);
        }
    }
    /**
     * A method whic removes a listin
     * @param listing The listing to remove
     */
    public void removeListing(JobListing listing) {
        if(companyListings.contains(listing)) {
            companyListings.remove(listing);
        }
    }

    /**
     * A getter method
     * @return The uuid
     */
    public String getUUID() {
        return this.uUID;
    }

    /**
     * A method which deletes a review
     * @param review The review to delete
     */
    public void deleteReview(Review review) {
        reviews.remove(review);
    }

    /**
     * A method which deletes a review
     * @param review THe review to remove
     */
    public void remove(Employer review) {
        review.remove(review);
    }

    /**
     * A method which deletes a review
     * @param review THe review to remove
     */
    public static void remove(Review review) {
        review.remove(review);
    }
}
