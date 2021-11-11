/**
 * @authors Colton Anderson
 * Instance of a Student
 */
import java.util.ArrayList;

public class Student extends User {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private ArrayList<JobListing> wishList;
    private ArrayList<Resume> resumes;
    private ArrayList<Review> reviews;

    //Constructors
    public Student(String username, String password, String studentID, String firstName, String lastName, String email, String phoneNumber) {
        super(username, password, "s", studentID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.resumes = new ArrayList<Resume>();
        this.reviews = new ArrayList<Review>();
        this.wishList = new ArrayList<JobListing>();
    }

    public Student(String username, String password, String studentID, String firstName, String lastName, String email, String phoneNumber, ArrayList<Resume> resumes, ArrayList<Review> reviews, ArrayList<JobListing> wishList)  {
        super(username, password, "s", studentID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.resumes = resumes;
        this.reviews = reviews;
        this.wishList = wishList;
    }



    /**
     * removes a reveiw
     * @param review
     */
    public void removeReview(Review review) {
        reviews.remove(review);
    }

    /**
     * adds item to a wishlist
     * @param jobListing
     */
    public void addToWishList(JobListing jobListing) {
        if(wishList.contains(jobListing)) {
            return;
        }
        wishList.add(jobListing);
    }

    /**
     * removes an item from the wishlist
     * @param jobListing
     */
    public void removeFromWishList(JobListing jobListing) {
        wishList.remove(jobListing);
    }

    /**
     * creates a reveiw for the employer
     * @param rating
     * @param review
     * @param reviewee
     */
    public void reviewEmployer(int rating, String review, User reviewee) {
        new Review(rating, review, getUUID(), reviewee.getUUID());
    }

    /**
     * returns the string of the student
     */
    public String toString() {
        return "First name: "+firstName+"\nLast name: "+lastName+"\nEmail: "+email+"\nPhone number: "+phoneNumber;
    }

    //getters
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public ArrayList<JobListing> getWishList() {
        return this.wishList;
    }

    public ArrayList<Resume> getResumes() {
        return this.resumes;
    }

    public ArrayList<Review> getReviews() {
        return this.reviews;
    }

    //setters
    public void setResumes(ArrayList<Resume> resumes) {
        this.resumes = resumes;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void addResume(Resume resume) {
        resumes.add(resume);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
 
}
