/**
 * @author Colton Anderson
 * Contains the Information for a Resume
 */
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Resume {
    private Student student;
    private ArrayList<Education> educations;
    private ArrayList<String> skills;
    private ArrayList<Experience> workExperiences;
    private ArrayList<Experience> extraCurriculars;

    /**
     * Constructs the resume with all of the perameters
     * @param student
     * @param educations
     * @param skills
     * @param workExperiences
     * @param extraCurriculars
     */
    public Resume(Student student, ArrayList<Education> educations, ArrayList<String> skills, ArrayList<Experience> workExperiences, ArrayList<Experience> extraCurriculars) {
        this.educations = educations;
        this.skills = skills;
        this.workExperiences = workExperiences;
        this.extraCurriculars = extraCurriculars;
        this.student = student;
    }

    /**
     * @overide initializes the arrays
     * @param student
     */
    public Resume(Student student) {
        this.student = student;
        this.educations = new ArrayList<Education>();
        this.skills = new ArrayList<String>();
        this.workExperiences = new ArrayList<Experience>();
        this.extraCurriculars = new ArrayList<Experience>();
    }

    /**
     * Adds a Skill
     * @param skill
     */
    public void addSkill(String skill) {
        skills.add(skill);
    }

    /**
     * Return the Array List of Educations
     * @return educations
     */
    public ArrayList<Education> getEducations() {
        return this.educations;
    }

    /**
     * Return the Array List of Skills
     * @return skills
     */
    public ArrayList<String> getSkills() {
        return this.skills;
    }

    /**
     * Return the Array List of workExperiences
     * @return workExpereinces
     */
    public ArrayList<Experience> getWorkExperiences() {
        return this.workExperiences;
    }

    /**
     * Return the Array List of Extra Curriculars
     * @return extraCurriculars;
     */
    public ArrayList<Experience> getExtraCurriculars() {
        return this.extraCurriculars;
    }

    /**
     * Adds a work Experience
     * @param experience
     */
    public void addWorkExperience(Experience experience) {
        workExperiences.add(experience);
    }

    /**
     * Prints the resume
     * @throws IOException
     */
    public void printResume() throws IOException{
        FileWriter file = new FileWriter("Resume.txt");
        PrintWriter output = new PrintWriter(file);
        output.println(toString());
        output.close();
    }

    /**
     * Adds an ExtraCurricular
     * @param experience
     */
    public void addExtraCurricular(Experience experience) {
        extraCurriculars.add(experience);
    }

    /**
     * Adds an Education
     * @param education
     */
    public void addEducation(Education education) {
        educations.add(education);
    }

    /**
     * Returns a String of the information in a resume
     */
    public String toString() {
        return student.getFirstName() + " " + student.getLastName() + "\nEmail: " + student.getEmail() + "\nPhone number: " + 
        student.getPhoneNumber() + "\nEducation:\n" + printEducation() + "Work Experience:\n" + 
        printExperiences(workExperiences) + "Extra Curriculars:\n" + printExperiences(extraCurriculars);
    }

    /**
     * Returns a String with the printed educations
     * @return String
     */
    private String printEducation() {
        String educationSection = "";

        for(int i = 0; i < educations.size(); i++) {
            educationSection += educations.get(i).toString() + "\n";
        }

        return educationSection;
    }

    /**
     * Returns a String with the printed experience
     * @param experiences
     * @return String
     */
    private String printExperiences(ArrayList<Experience> experiences) {
        String experienceSection = "";

        for(int i = 0; i < experiences.size(); i++) {
            experienceSection = experienceSection + experiences.get(i).toString() + "\n";
        }

        return experienceSection;
    }

}
