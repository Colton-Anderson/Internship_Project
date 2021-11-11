/**
 * Creates an Instance of the Job Listing application which will call apon the job listing list
 * and user list
 */
import java.util.ArrayList;

public class JobListingApplication {

    private UserList users;
    private JobListingsList jobs;
    private User user;
    private Student studentUser;
    private Employer employerUser;
    private static JobListingApplication jobListingApplication;

    /**
     * Constructor
     */
    private JobListingApplication() {
        users = UserList.getInstance();
        jobs = JobListingsList.getInstance();
        jobs.setEmployers(users.getEmployers());
    }

    /**
     * Creates a student account
     * @param username
     * @param password
     * @param studentID
     * @param firstName
     * @param lastName
     * @param email
     * @param phoneNumber
     * @return true if account is created
     */
    public boolean createStudentAccount(String username, String password, String studentID, String firstName, String lastName, String email, String phoneNumber) {
        if(!users.findAccount(username,password)) {
            users.addStudent(new Student(username, password, studentID, firstName, lastName, email, phoneNumber));
            return true;
        } else {
            return false;
        }
    }


    /**
     * edits the student name
     * @param firstName
     * @param lastName
     */
    public void editStudentName(String firstName, String lastName) {
        studentUser.setFirstName(firstName);
        studentUser.setLastName(lastName);
        for(Student student : users.getStudents()) {
            if(student.getUUID().equalsIgnoreCase(studentUser.getUUID())) {
                student.setFirstName(firstName);
                student.setLastName(lastName);
            }
        }
    }

    /**
     * Creates resume
     * @return
     */
    public Resume createResume() {
        return new Resume(studentUser);
    }


    /**
     * Adds a resume to the list of resumes
     * @param resume
     */
    public void addResume(Resume resume) {
        studentUser.addResume(resume);
        users.addResume(studentUser, resume);
    }

    /**
     * Returns a list og resumes
     * @return
     */
    public ArrayList<Resume> getStudentResumes() {
        return studentUser.getResumes();
    }

    /**
     * creates a job listing
     * @param title
     * @param postedDate
     * @param expirationDate
     * @param location
     * @param jobPay
     * @return
     */
    public JobListing createListing(String title, String postedDate, String expirationDate, String location, String jobPay) {
        return new JobListing(title, postedDate, expirationDate, location, Integer.parseInt(jobPay), employerUser.getUUID());
    }

    /**
     * adds a job listing to a list of job listings
     * @param listing
     */
    public void addJobListing(JobListing listing) {
        employerUser.addListing(listing);
        users.addListing(listing);
        jobs.addListing(listing);
    }

    /**
     * creates an employer account
     * @param username
     * @param password
     * @param aName
     * @param aDescription
     * @param aLocation
     * @return true if account is found
     */
    public boolean createEmployerAccount(String username, String password, String aName, String aDescription, String aLocation) {
        if(!users.findAccount(username,password)) {
            users.addEmployer(new Employer(username, password, aName, aDescription, aLocation));
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns an instance of the job listing aplication
     * @return joblistingapplication
     */
    public static JobListingApplication getInstance() {
        if(jobListingApplication == null) {
            return new JobListingApplication();
        }
        return jobListingApplication;
    }

    /**
     * checks to see if the login is a success
     * @param username
     * @param password
     * @return true if login was successful
     */
    public boolean login(String username, String password) {
        if(users.login(username, password) == null) {
            return false;
        }
        user = users.login(username, password);
        setChild(user);
        return true;
    }

    /**
     * accesses the type of user and then creates a user based on that type
     * @param user
     */
    public void setChild(User user) {
        if(user.getType().equalsIgnoreCase("s") && (loginStudent(user) != null)) {
            this.user = loginStudent(user);
            this.studentUser = loginStudent(user);
        }
        else if(user.getType().equalsIgnoreCase("e") && (loginEmployer(user) != null)) {
            this.user = loginEmployer(user);
            this.employerUser = loginEmployer(user);
            setEmployerUserListings();
        }
    }

    /**
     * Sets listings to specific employers
     */
    private void setEmployerUserListings() {
        for(JobListing listing : jobs.getJobListings()) {
            if(listing.getEmployerID().equalsIgnoreCase(employerUser.getUUID())) {
                employerUser.addListing(listing);
            }
        }
    }

    /**
     * returns the type of account for each user
     * @return
     */
    public String findAccountType() {
        return user.getType();
    }

    /**
     * returns student if the user ID is associated with a student ID
     * @param user
     * @return
     */
    public Student loginStudent(User user) {
        ArrayList<Student> students = users.getStudents();
        for(Student student : students) {
            if(user.getUUID() == student.getUUID()) {
                return student;
            }
        }
        return null;
    }

    /**
     * returns a list of the job listings
     * @return
     */
    public ArrayList<JobListing> getUserListings() {
        return employerUser.getCompanyListings();
    }

    /**
     * Returns the employer when the ids match
     * @param user
     * @return
     */
    public Employer loginEmployer(User user) {
        ArrayList<Employer> employers = users.getEmployers();
        for(Employer employer : employers) {
            if(user.getUUID() == employer.getUUID()) {
                return employer;
            }
        }
        return null;
    }

    /**
     * returns the admin when the user ID matches
     * @param user
     * @return
     */
    public Admin loginAdmin(User user) {
        ArrayList<Admin> admins = users.getAdmins();
        for(Admin admin : admins) {
            if(user.getUUID() == admin.getUUID()) {
                return admin;
            }
        }
        return null;
    }

    /**
     * calls the search listings method with the keyword perameter
     * @param keyword
     * @return
     */
    public ArrayList<JobListing> searchListings(String keyword) {
        return jobs.searchListings(keyword);
    }


    /**
     * saves the users upon logout to the json files
     */
    public void logout() {
        DataWriter.saveUsers(users.getUsers(), users.getEmployers(), users.getStudents());
        DataWriter.saveJobListing(jobs.getJobListings());
        user = null; studentUser = null; employerUser = null;
    }

    public Employer getEmployerUser() {
        return employerUser;
    }

    /**
     * adds the student user to the applied students withing the job listing
     * @param listing
     */
    public void applyStudent(JobListing listing) {
        if(!listing.getApplicantIDS().contains(studentUser.getUUID())) {
            listing.apply(studentUser);
            jobs.updateListing(listing);
        }
    }

    public ArrayList<Student> getStudents() {
        return users.getStudents();
    }

    public boolean checkForUsername(String username) {
        if(users.containsUser(username)) {
            return true;
        }
        return false;
    }
}
