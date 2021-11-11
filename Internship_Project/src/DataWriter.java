import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * Writes data to json file
 * @author Mitchell Carroll
 */
public class DataWriter extends DataConstants {

    /**
     * Saves users given an arraylist of users
     * @param users arraylist of users
     */
    public static void saveUsers(ArrayList<User> users, ArrayList<Employer> employers, ArrayList<Student> students) {

        /**
         * variables
         */
        ArrayList<Admin> admins = UserList.getInstance().getAdmins();
        JSONArray jsonUsers  = new JSONArray();

        /**
         * loops through users, gets their type, and adds them to JSONArray
         */
        for(User user : users) {
            if(user.getType().equalsIgnoreCase("s")) {
                if(!jsonUsers.contains(getStudentJSON(user, students))) {
                    jsonUsers.add(getStudentJSON(user, students));
                }
            }
            else if(user.getType().equalsIgnoreCase("e")) {
                if(!jsonUsers.contains(getEmployerJSON(user, employers))) {
                    jsonUsers.add(getEmployerJSON(user, employers));
                }
            }
            else if(user.getType().equalsIgnoreCase("a")) {
                if(!jsonUsers.contains(getAdmin(user, admins))) {
                    jsonUsers.add(getAdminJSON(user, admins));
                }
            }

            try (FileWriter file = new FileWriter(USER_FILE_NAME)) {

                file.write(jsonUsers.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * saves an arraylist of job listings
     * @param listings arraylist of job listings
     */
    public static void saveJobListing(ArrayList<JobListing> listings) {
        JSONArray jobListingsJSON = new JSONArray();

        for(JobListing listing : listings) {
            if((!jobListingsJSON.contains(listing))) {
                jobListingsJSON.add(getListingJSON(listing));
            }
        }

        try (FileWriter file = new FileWriter(LISTING_FILE_NAME)) {

            file.write(jobListingsJSON.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * makes JSONObject of a listing
     * @param listing a listing
     * @return listing as JSONObject
     */
    private static JSONObject getListingJSON(JobListing listing) {
        JSONObject listingJSON = new JSONObject();
        listingJSON.put(JOB_LISTING_ID, listing.getId().toString());
        listingJSON.put(JOB_LISTING_TITLE, listing.getTitle().toString());
        listingJSON.put(JOB_POSTED_DATE, listing.getPostedDate().toString());
        listingJSON.put(JOB_EXPIRATION_DATE, listing.getExpirationDate().toString());
        listingJSON.put(JOB_DESIRED_SKILLS, getDesiredSkillsJSON(listing));
        listingJSON.put(JOB_LOCATION, listing.getLocation());
        listingJSON.put(JOB_PAY, String.valueOf(listing.getJobPay()));
        listingJSON.put(JOB_APPLICANT_IDS, getApplicantIDSJSON(listing));
        listingJSON.put(JOB_EMPLOYER_ID, listing.getEmployerID().toString());
        return listingJSON;
    }

    /**
     * loops through applicants of a listing and adds them to a JSONArray
     * @param listing a job listing
     * @return JSONArray of applicants
     */
    private static JSONArray getApplicantIDSJSON(JobListing listing) {
        JSONArray applicantIDSJSON = new JSONArray();
        for(String id : listing.getApplicantIDS()) {
            JSONObject applicantIDJSON = new JSONObject();
            applicantIDJSON.put(APPLICANT_ID, id);
            applicantIDSJSON.add(applicantIDJSON);
        }
        return applicantIDSJSON;
    }

    /**
     * loops through skills of a listing and adds them to a JSONArray
     * @param listing a job listing
     * @return JSONArray of skills
     */
    private static JSONArray getDesiredSkillsJSON(JobListing listing) {
        JSONArray skillsJSON = new JSONArray();
        for(String skill : listing.getDesiredSkills()) {
            JSONObject skillJSON = new JSONObject();
            skillJSON.put(SKILLS_SKILL, skill.toString());
            skillsJSON.add(skillJSON);
        }
        return skillsJSON;
    }

    /**
     * gets instance of a student given a user
     * @param user a user
     * @return a student
     */
    private static Student getStudent(User user, ArrayList<Student> students) {
        for(int i=0;i<students.size();i++) {
            if(students.get(i).getUUID().equalsIgnoreCase(user.getUUID())) {
                return students.get(i);
            }
        }
        return null;
    }

    /**
     * gets instance of an employer given a user
     * @param user a user
     * @return an employer
     */
    private static Employer getEmployer(User user, ArrayList<Employer> employers) {
        for(int i=0;i<employers.size();i++) {
            if(employers.get(i).getUUID().equalsIgnoreCase(user.getUUID())) {
                return employers.get(i);
            }
        }
        return null;
    }

    /**
     * gets instance of an admin given a user
     * @param user a user
     * @return an admin
     */
    private static Admin getAdmin(User user, ArrayList<Admin> admins) {
        for(int i=0;i<admins.size();i++) {
            if(admins.get(i).getUUID().equalsIgnoreCase(user.getUUID())) {
                return admins.get(i);
            }
        }
        return null;
    }

    /**
     * makes JSONObject of a student
     * @param user a user
     * @return student as JSONObject
     */
    public static JSONObject getStudentJSON(User user, ArrayList<Student> students) {
        if(getStudent(user, students) == null) {
            return null;
        }
        Student student = getStudent(user, students);
        JSONObject studentJSON = new JSONObject();
        studentJSON.put(USER_USERNAME, student.getUsername().toString());
        studentJSON.put(USER_PASSWORD, student.getPassword().toString());
        studentJSON.put(USER_TYPE, student.getType().toString());
        studentJSON.put(USER_UUID, student.getUUID().toString());
        studentJSON.put(STUDENT_FIRST_NAME, student.getFirstName().toString());
        studentJSON.put(STUDENT_LAST_NAME, student.getLastName().toString());
        studentJSON.put(STUDENT_EMAIL, student.getEmail().toString());
        studentJSON.put(STUDENT_PHONE, student.getPhoneNumber().toString());
        studentJSON.put(STUDENT_RESUMES, getStudentResumesJSON(student));
        studentJSON.put(USER_REVIEWS, getStudentReviews(student));
        return studentJSON;
    }

    /**
     * makes JSONArray of resumes given a student
     * @param student a student
     * @return resumes as JSONArray
     */
    private static JSONArray getStudentResumesJSON(Student student) {
        JSONArray studentResumesJSON = new JSONArray();
        for(int i=0;i<student.getResumes().size();i++) {
            JSONObject resumeJSON = new JSONObject();
            Resume resume = student.getResumes().get(i);
            resumeJSON.put(RESUME_EDUCATIONS, getResumeEducations(resume));
            resumeJSON.put(RESUME_SKILLS, getResumeSkills(resume));
            resumeJSON.put(RESUME_WORK, getResumeExperiences(resume.getWorkExperiences()));
            resumeJSON.put(RESUME_EXTRACURRICULARS, getResumeExperiences(resume.getExtraCurriculars()));
            studentResumesJSON.add(resumeJSON);
        }
        return studentResumesJSON;
    }

    /**
     * makes JSONArray of educations given a resume
     * @param resume a resume
     * @return educations as JSONArray
     */
    public static JSONArray getResumeEducations(Resume resume) {
        JSONArray educationsJSON = new JSONArray();
        for(int i=0;i<resume.getEducations().size();i++) {
            Education education = resume.getEducations().get(i);
            JSONObject educationJSON = new JSONObject();
            educationJSON.put(EDUCATIONS_SCHOOL, education.getSchool().toString());
            educationJSON.put(EDUCATIONS_YEAR, String.valueOf(education.getClassYear()));
            educationJSON.put(EDUCATIONS_MAJOR, education.getMajor().toString());
            educationJSON.put(EDUCATIONS_MINOR, education.getMinor().toString());
            educationJSON.put(EDUCATIONS_GPA, String.valueOf(education.getGpa()));
            educationsJSON.add(educationJSON);
        }
        return educationsJSON;
    }

    /**
     * makes JSONArray of skills given a resume
     * @param resume a resume
     * @return skills as JSONArray
     */
    private static JSONArray getResumeSkills(Resume resume) {
        JSONArray skillsJSON = new JSONArray();
        for(int i=0;i<resume.getSkills().size();i++) {
            JSONObject skillJSON = new JSONObject();
            skillJSON.put(SKILLS_SKILL, resume.getSkills().get(i).toString());
            skillsJSON.add(skillJSON);
        }
        return skillsJSON;
    }

    /**
     * makes a JSONArray of experiences given an arraylist of experiences
     * @param experiences an arraylist of experiences
     * @return JSONArray of experiences
     */
    public static JSONArray getResumeExperiences(ArrayList<Experience> experiences) {
        JSONArray experiencesJSON = new JSONArray();
        for(int i=0;i<experiences.size();i++) {
            JSONObject experienceJSON = new JSONObject();
            Experience experience = experiences.get(i);
            experienceJSON.put(EXPERIENCE_TITLE, experience.getTitle().toString());
            experienceJSON.put(EXPERIENCE_START, experience.getStartDate().toString());
            experienceJSON.put(EXPERIENCE_END, experience.getEndDate().toString());
            experienceJSON.put(EXPERIENCE_DESCRIPTION, experience.getDescription().toString());
            experiencesJSON.add(experienceJSON);
        }
        return experiencesJSON;
    }

    /**
     * makes a JSONArray of reviews given a student
     * @param student a student 
     * @return JSONArray of reviews
     */
    public static JSONArray getStudentReviews(Student student) {
        JSONArray reviewsJSON = new JSONArray();
        for(int i=0;i<student.getReviews().size();i++) {
            JSONObject reviewJSON = new JSONObject();
            Review review = student.getReviews().get(i);
            reviewJSON.put(REVIEW_RATING, String.valueOf(review.getRating()));
            reviewJSON.put(REVIEW_REVIEW, review.getReview());
            reviewJSON.put(REVIEW_REVIEWEE, review.getRevieweeID());
            reviewJSON.put(REVIEW_REVIEWER, review.getReviewerID());
            reviewsJSON.add(reviewJSON);
        }
        return reviewsJSON;
    }

    /**
     * makes JSONObject of employer given a user
     * @param user a user
     * @return JSONObject of employer
     */
    private static JSONObject getEmployerJSON(User user, ArrayList<Employer> employers) {
        JSONObject employerJSON = new JSONObject();
        Employer employer = getEmployer(user, employers);
        employerJSON.put(USER_USERNAME, employer.getUsername().toString());
        employerJSON.put(USER_PASSWORD, employer.getPassword().toString());
        employerJSON.put(USER_TYPE, employer.getType().toString());
        employerJSON.put(USER_UUID, employer.getUUID().toString());
        employerJSON.put(EMPLOYER_NAME, employer.getCompanyName().toString());
        employerJSON.put(EMPLOYER_DESCRIPTION, employer.getCompanyDescription().toString());
        employerJSON.put(EMPLOYER_LOCATION, employer.getCompanyLocation().toString());
        employerJSON.put(USER_REVIEWS, getEmployerReviews(employer));
        return employerJSON;
    }

    /**
     * makes JSONArray of reviews given an employer
     * @param employer an employer 
     * @return JSONArray of reviews 
     */
    private static JSONArray getEmployerReviews(Employer employer) {
        JSONArray reviewsJSON = new JSONArray();
        for(int i=0;i<employer.getReviews().size();i++) {
            JSONObject reviewJSON = new JSONObject();
            Review review = employer.getReviews().get(i);
            reviewJSON.put(REVIEW_RATING, String.valueOf(review.getRating()));
            reviewJSON.put(REVIEW_REVIEW, review.getReview());
            reviewJSON.put(REVIEW_REVIEWEE, review.getRevieweeID());
            reviewJSON.put(REVIEW_REVIEWER, review.getReviewerID());
            reviewsJSON.add(reviewJSON);
        }
        return reviewsJSON;
    }

    /**
     * makes JSONObject of admin given a user
     * @param user a user
     * @return JSONObject of admin
     */
    private static JSONObject getAdminJSON(User user, ArrayList<Admin> admins) {
        JSONObject adminJSON = new JSONObject();
        Admin admin = getAdmin(user, admins);
        adminJSON.put(USER_USERNAME, admin.getUsername().toString());
        adminJSON.put(USER_PASSWORD, admin.getPassword().toString());
        adminJSON.put(USER_TYPE, admin.getType().toString());
        adminJSON.put(USER_UUID, admin.getUUID().toString());
        return adminJSON;
    }
}
