import java.util.ArrayList;
import java.util.Random;
/**
 * @author Carter Chandler, Lauren Hadlow
 * Class that creates objects for job listings
 */

 public class JobListing {

    private String id;
    private String title;
    private String postedDate;
    private String expirationDate;
    private ArrayList<String> desiredSkills;
    private String location;
    private int jobPay;
    private ArrayList<Student> applicants;
    private ArrayList<String> applicantIDS;
    private String employerID;
    private Employer employer;

    /**
     * A constructor
     * @param id The listing's id
     * @param title The title of the listing
     * @param postedDate The posted date of the listing
     * @param expirationDate The expiration date of the listing
     * @param location The location of the listing
     * @param jobPay The pay of the listing
     * @param employerID The id for the employer who posted the listing
     */
    public JobListing(String id, String title, String postedDate, String expirationDate, String location, int jobPay, String employerID) {
        this.id = id;
        this.title = title;
        this.postedDate = postedDate;
        this.expirationDate = expirationDate;
        this.location = location;
        this.jobPay = jobPay;
        this.employerID = employerID;
        desiredSkills = new ArrayList<String>();
        applicants = new ArrayList<Student>();
        applicantIDS = new ArrayList<String>();
    }

    /**
     * A constructor
     * @param title The title of the listing
     * @param postedDate The posted date of the listing
     * @param expirationDate The expiration date of the listing
     * @param location The location of the listing
     * @param jobPay The pay of the listing
     * @param employerID The id for the employer who posted the listing
     */
    public JobListing(String title, String postedDate, String expirationDate, String location, int jobPay, String employerID) {
        this.id = createID();
        this.title = title;
        this.postedDate = postedDate;
        this.expirationDate = expirationDate;
        this.desiredSkills = new ArrayList<String>();
        this.location = location;
        this.jobPay = jobPay;
        this.employerID = employerID;
        this.applicantIDS = new ArrayList<String>();
        this.applicants = new ArrayList<Student>();
    }

    /**
     * A constructor
     * @param title The title of the listing
     * @param postedDate The posted date of the listing
     * @param expirationDate The expiration date of the listing
     * @param desiredSkills The desired skills for the listing
     * @param applicantIDS The ids of the students who have applied for the job
     * @param location The location of the listing
     * @param jobPay The pay of the listing
     * @param employerID The id for the employer who posted the listing
     */
    public JobListing(String title, String postedDate, String expirationDate, ArrayList<String> desiredSkills, ArrayList<String> applicantIDS, String location, int jobPay, String employerID) {
        this.id = createID();
        this.title = title;
        this.postedDate = postedDate;
        this.expirationDate = expirationDate;
        this.desiredSkills = desiredSkills;
        this.location = location;
        this.jobPay = jobPay;
        this.applicantIDS = applicantIDS;
        this.employerID = employerID;
    }

    /**
     * A constructor
     * @param title The title of the listing
     * @param id The listing's id
     * @param postedDate The posted date of the listing
     * @param expirationDate The expiration date of the listing
     * @param desiredSkills The desired skills for the listing
     * @param applicantIDS The ids of the students who have applied for the job
     * @param location The location of the listing
     * @param jobPay The pay of the listing
     * @param employerID The id for the employer who posted the listing
     */
    public JobListing(String title, String id, String postedDate, String expirationDate, ArrayList<String> desiredSkills, ArrayList<String> applicantIDS, String location, int jobPay, String employerID) {
        this.id = id;
        this.title = title;
        this.postedDate = postedDate;
        this.expirationDate = expirationDate;
        this.desiredSkills = desiredSkills;
        this.location = location;
        this.jobPay = jobPay;
        this.applicantIDS = applicantIDS;
        this.employerID = employerID;
    }

    /**
     * A setter
     * @param aEmployer The employer who posted the listing
     */
    public void setEmployer(Employer aEmployer) {
        this.employer = aEmployer;
    }

    /**
     * A getter
     * @return The listing id
     */
    public String getId() {
        return this.id;
    }
    /**
     * A getter
     * @return The listing's posted date
     */
    public String getPostedDate() {
        return this.postedDate;
    }
    /**
     * A getter
     * @return The listing's expiration date
     */
    public String getExpirationDate() {
        return this.expirationDate;
    }
    /**
     * A getter
     * @return The desired skills for the listing
     */
    public ArrayList<String> getDesiredSkills() {
        return this.desiredSkills;
    }
    /**
     * A getter
     * @return The location of the listing
     */
    public String getLocation() {
        return this.location;
    }
    /**
     * A getter
     * @return The pay of the listing
     */
    public int getJobPay() {
        return this.jobPay;
    }
    /**
     * A getter
     * @return The applicants for the listing
     */
    public ArrayList<Student> getApplicants() {
        return this.applicants;
    }
    /**
     * A getter
     * @return The employer who posted the listing
     */
    public Employer getEmployer() {
        return this.employer;
    }

    /**
     * A method used by students to apply for the job
     * @param student The student who is applying
     */
    public void apply(Student student) {
        applicants.add(student);
        applicantIDS.add(student.getUUID());
    }

    /**
     * A method which returns the listing as a string
     * @return The listing converted to a string
     */
    public String toString() {
        String desiredSkillsString = "";
        for(int i = 0; i<desiredSkills.size(); i++) {
            desiredSkillsString+=desiredSkills.get(i);
            if(i<desiredSkills.size()-1) {
                desiredSkillsString+=", ";
            }
        }
        String pay = "\n";
        if(this.jobPay == 0) {
            pay += "Internship";
        }
        else if(this.jobPay > 0) {
            pay += "Job pay: "+jobPay;
        }
        return title+"\nEmployer: "+this.employer.getCompanyName()+"\nLocation: "+this.location+pay+"\nExpiration Date: "+this.expirationDate+"\nPosted Date: "+this.postedDate+"\nDesired Skills: "+desiredSkillsString;
    }

    /**
     * A getter
     * @return The listing's title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * A method which sorts the applicants
     * @param sortType The way the applicants will be sorted
     * @return The sorted array list
     */
    public ArrayList<Student> sortApplicants(ApplicantSortType sortType) {
        if(sortType.equals("nameAToZ")) {
            applicants = sortAToZHelper(applicants);
        }
        else if (sortType.equals("nameZToA")) {
            applicants = sortZToAHelper(applicants);
        }
        return applicants;
    }

    /**
     * A getter
     * @return The applicant's ids
     */
    public ArrayList<String> getApplicantIDS() {
        return this.applicantIDS;
    }

    /**
     * A method which removes an applicant from the listing
     * @param applicant The applicant to remove
     */
    public void removeApplicant(Student applicant) {
        applicants.remove(applicant);
    }

    /**
     * A method which displays the applicants
     * @return A string representation of all applicants
     */
    public String viewApplicants() {
        String applicantsString = "";
        for(int i = 0; i < applicants.size(); i++) {
            applicantsString+=applicants.get(i).getFirstName()+" "+applicants.get(i).getLastName()+"\n";
        }
        return applicantsString;
    }

    /**
     * A method which creates a uuid
     * @return The uuid
     */
    public String createID() {
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        return String.valueOf(alphabet.charAt(random.nextInt(alphabet.length())))+uUIDNumbers();
    }

    /**
     * A method which returns a uuid
     * @return A uuid
     */
    private String uUIDNumbers() {
        Random random = new Random();
        String ret = "";
        for(int i=0;i<6;i++) {
            ret += random.nextInt(9);
        }
        return ret;
    }
    /**
     * A method which helps sort applicants
     * @param applicants The applicants to be sorted
     * @return THe sorted list
     */
    private ArrayList<Student> sortAToZHelper(ArrayList<Student> applicants) {
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for(int i = 0; i<applicants.size()-1; i++) {
                if(applicants.get(i).getLastName().compareTo(applicants.get(i+1).getLastName())>0) {
                    Student temp = applicants.get(i+1);
                    applicants.set(i+1,applicants.get(i));
                    applicants.set(i,temp);
                }
            }
        }
        return applicants;
    }
    /**
     * A method which helps sort applicants
     * @param applicants The applicants to be sorted
     * @return THe sorted list
     */
    private ArrayList<Student> sortZToAHelper(ArrayList<Student> applicants) {
        boolean sorted = false;
        while(!sorted) {
            sorted = true;
            for(int i = 0; i>applicants.size()-1; i++) {
                if(applicants.get(i).getLastName().compareTo(applicants.get(i+1).getLastName())>0) {
                    Student temp = applicants.get(i+1);
                    applicants.set(i+1,applicants.get(i));
                    applicants.set(i,temp);
                }
            }
        }
        return applicants;
    }

    /**
     * A getter
     * @return THe listing's uuid
     */
    public String getUUID() {
        return this.id;
    }

    /**
     * A setter
     * @param desiredSkills THe desired skills for the listing
     */
    public void setDesiredSkills(ArrayList<String> desiredSkills) {
        this.desiredSkills = desiredSkills;
    }

    /**
     * A setter
     * @param applicantIDS The applicant ids
     */
    public void setApplicantIDS(ArrayList<String> applicantIDS) {
        this.applicantIDS = applicantIDS;
    }

    /**
     * A getter
     * @return The employer's id
     */
    public String getEmployerID() {
        return this.employerID;
    }

    /**
     * A method which adds a desired skill to the desired skills list
     * @param skill The skill to be added
     */
    public void addDesiredSkill(String skill) {
        desiredSkills.add(skill);
    }
 }
