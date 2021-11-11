import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Loads data from JSON file
 * @author Mitchell Carroll
 */
public class DataLoader extends DataConstants{

	/**
	 * Variables
	 */
	private static ArrayList<Student> students = new ArrayList<Student>();
	private static ArrayList<Employer> employers = new ArrayList<Employer>();
	private static ArrayList<Admin> admins = new ArrayList<Admin>();

	/**
	 * gets arraylist of users
	 * @return arraylist of users
	 */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
			/**
			 * parses json file into array
			 */
			FileReader reader = new FileReader(USER_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray peopleJSON = (JSONArray)parser.parse(reader);

			/**
			 * iterates over array and add users to list 
			 */
			for(int i=0;i<peopleJSON.size();i++) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				String username = (String)personJSON.get(USER_USERNAME);
				String password = (String)personJSON.get(USER_PASSWORD);
				String userType = (String)personJSON.get(USER_TYPE);
				String uUID = (String)personJSON.get(USER_UUID);
				/**
				 * adds student to user list
				 */
				if(userType.equalsIgnoreCase("s")) {
					Student student = loadStudent(username, password, uUID, personJSON);
					student.setResumes(loadResumes(personJSON, student));
					student.setReviews(loadReviews(personJSON));
					users.add(student);
					students.add(student);
				}
				/**
				 * adds employer to user list
				 */
				else if(userType.equalsIgnoreCase("e")) {
					Employer employer = loadEmployer(username, password, uUID, personJSON);
					users.add(employer);
					employers.add(employer);
				}
				/**
				 * adds admin to user list
				 */
				else if(userType.equalsIgnoreCase("a")) {
					Admin admin = new Admin(username, password, uUID);
					users.add(admin);
					admins.add(admin);
				}
			}
			return users;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
    }

	/**
	 * return arraylist of students
	 * @return students
	 */
	public static ArrayList<Student> getStudents() {
		return students;
	}

	/**
	 * return arraylist of employers
	 * @return employers
	 */
	public static ArrayList<Employer> getEmployers() {
		return employers;
	}

	/**
	 * return admins
	 * @return admins
	 */
	public static ArrayList<Admin> getAdmins() {
		return admins;
	}

	/**
	 * parses job listings from json file
	 * @return arraylist of job listings
	 */
	public static ArrayList<JobListing> getJobListings() {
		ArrayList<JobListing> listings = new ArrayList<JobListing>();
		try {
			FileReader reader = new FileReader(LISTING_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray listingsJSON = (JSONArray)parser.parse(reader);
			for(int i=0;i<listingsJSON.size();i++) {
				JSONObject listingJSON = (JSONObject)listingsJSON.get(i);
				String listingID = (String)listingJSON.get(JOB_LISTING_ID);
				String title = (String)listingJSON.get(JOB_LISTING_TITLE);
				String postedDate = (String)listingJSON.get(JOB_POSTED_DATE);
				String expirationDate = (String)listingJSON.get(JOB_EXPIRATION_DATE);
				String location = (String)listingJSON.get(JOB_LOCATION);
				int pay = Integer.parseInt((String)listingJSON.get(JOB_PAY));
				String employerID = (String)listingJSON.get(JOB_EMPLOYER_ID);
				JobListing listing = new JobListing(listingID, title, postedDate, expirationDate, location, pay, employerID);
				//gets desired skills
				ArrayList<String> desiredSkills = new ArrayList<String>();
				JSONArray skillsArray = (JSONArray)listingJSON.get(JOB_DESIRED_SKILLS);
				for(int j=0;j<skillsArray.size();j++) {
					JSONObject skillJSON = (JSONObject)skillsArray.get(j);
					String skill = (String)skillJSON.get(SKILLS_SKILL);
					desiredSkills.add(skill);
				}
				listing.setDesiredSkills(desiredSkills);

				//gets applicant ids
				ArrayList<String> applicantIDS = new ArrayList<String>();
				JSONArray applicantIDArray = (JSONArray)listingJSON.get(JOB_APPLICANT_IDS);
				for(int j=0;j<applicantIDArray.size();j++) {
					JSONObject applicantIDJSON = (JSONObject)applicantIDArray.get(j);
					String applicantID = (String)applicantIDJSON.get(APPLICANT_ID);
					applicantIDS.add(applicantID);
				}
				//sets applicants
				listing.setApplicantIDS(applicantIDS);
				for(int j=0;j<applicantIDS.size();j++) {
					for(int k=0;k<students.size();k++) {
						if(students.get(k).getUUID() == applicantIDS.get(j)) {
							listing.apply(students.get(k));
						}
					}
				}
				if(!listings.contains(listing)) {
					listings.add(listing);
				}
				
			}
			return listings;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * retuens student
	 * @param username
	 * @param password
	 * @param uUID
	 * @param personJSON JSON object
	 * @return instance of student
	 */
	private static Student loadStudent(String username, String password, String uUID, JSONObject personJSON) {
		String firstName = (String)personJSON.get(STUDENT_FIRST_NAME);
		String lastName = (String)personJSON.get(STUDENT_LAST_NAME);
		String email = (String)personJSON.get(STUDENT_EMAIL);
		String phoneNumber = (String)personJSON.get(STUDENT_PHONE);
		return new Student(username, password, uUID, firstName, lastName, email, phoneNumber);
	}

	/**
	 * retuens employer
	 * @param username
	 * @param password
	 * @param uUID
	 * @param personJSON JSON object
	 * @return instance of Employer
	 */
	private static Employer loadEmployer(String username, String password, String uUID, JSONObject personJSON) {
		String name = (String)personJSON.get(EMPLOYER_NAME);
		String description = (String)personJSON.get(EMPLOYER_DESCRIPTION);
		String location = (String)personJSON.get(EMPLOYER_LOCATION);
		return new Employer(uUID, username, password, name, description, location);
	}

	/**
	 * Loads resumes
	 * @param personJSON person whos resumes are being loaded
	 * @param student student who resumes should be added to
	 * @return ArrayList of type resume
	 */
	private static ArrayList<Resume> loadResumes(JSONObject personJSON, Student student) {
		ArrayList<Resume> ret = new ArrayList<Resume>();
		JSONArray resumes = (JSONArray)personJSON.get(STUDENT_RESUMES);
		for(int i=0;i<resumes.size();i++) {
			JSONObject resume = (JSONObject)resumes.get(i);

			JSONArray educationsArray = (JSONArray)resume.get(RESUME_EDUCATIONS);
			ArrayList<Education> educations = new ArrayList<Education>();
			for(int j=0;j<educationsArray.size();j++) {
				JSONObject education = (JSONObject)educationsArray.get(j);
				String school = (String)education.get(EDUCATIONS_SCHOOL);
				int classYear = Integer.parseInt((String)education.get(EDUCATIONS_YEAR));
				String major = (String)education.get(EDUCATIONS_MAJOR);
				String minor = (String)education.get(EDUCATIONS_MINOR);
				double gpa = Double.parseDouble((String)education.get(EDUCATIONS_GPA));
				educations.add(new Education(school, classYear, major, minor, gpa));
			}

			ArrayList<String> skills = new ArrayList<String>();
			JSONArray skillsArray = (JSONArray)resume.get(RESUME_SKILLS);
			for(int k=0;k<skillsArray.size();k++) {
				JSONObject skill = (JSONObject)skillsArray.get(k);
				skills.add((String)skill.get(SKILLS_SKILL));
			}

			ArrayList<Experience> workExperiences = new ArrayList<Experience>();
			JSONArray workArray = (JSONArray)resume.get(RESUME_WORK);
			for(int k=0;k<workArray.size();k++) {
					JSONObject experience = (JSONObject)workArray.get(k);
					String title = (String)experience.get(EXPERIENCE_TITLE);
					String startDate = (String)experience.get(EXPERIENCE_START);
					String endDate = (String)experience.get(EXPERIENCE_END);
					String description = (String)experience.get(EXPERIENCE_DESCRIPTION);
					workExperiences.add(new Experience(title, startDate, endDate, description));
			}

			ArrayList<Experience> extraCurriculars = new ArrayList<Experience>();
			JSONArray extraArray = (JSONArray)resume.get(RESUME_EXTRACURRICULARS);
			for(int k=0;k<extraArray.size();k++) {
					JSONObject experience = (JSONObject)extraArray.get(k);
					String title = (String)experience.get(EXPERIENCE_TITLE);
					String startDate = (String)experience.get(EXPERIENCE_START);
					String endDate = (String)experience.get(EXPERIENCE_END);
					String description = (String)experience.get(EXPERIENCE_DESCRIPTION);
					extraCurriculars.add(new Experience(title, startDate, endDate, description));
			}

			ret.add(new Resume(student, educations, skills, workExperiences, extraCurriculars));
		}
		return ret;
	}

	/**
	 * gets reviews for employer or student
	 * @param personJSON JSON object
	 * @return arraylist of reviews
	 */
	private static ArrayList<Review> loadReviews(JSONObject personJSON) {
		ArrayList<Review> ret = new ArrayList<Review>();
		JSONArray reviewsArray = (JSONArray)personJSON.get(USER_REVIEWS);
		for(int i=0;i<reviewsArray.size();i++) {
			JSONObject review = (JSONObject)reviewsArray.get(i);
			int rating = Integer.parseInt((String)review.get(REVIEW_RATING));
			String reviewDes = (String)review.get(REVIEW_REVIEW);
			String revieweeID = (String)review.get(REVIEW_REVIEWEE);
			String reviewerID = (String)review.get(REVIEW_REVIEWER);
			ret.add(new Review(rating, reviewDes, reviewerID, revieweeID));
		}
		return ret;
	}

}
