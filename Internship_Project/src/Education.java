/**
 * @author Colton Anderson
 * Contain the information of the type Education
 */
public class Education {
    private String school;
    private int classYear;
    private String major;
    private String minor;
    private double gpa;

    /**
     * Constructs the type Education
     * @param school: Name of the School
     * @param classYear
     * @param major
     * @param minor
     * @param gpa
     */
    public Education(String school, int classYear, String major, String minor, double gpa) {
        this.school = school;
        this.classYear = classYear;
        this.major = major;
        this.minor = minor;
        this.gpa = gpa;
    }

    /**
     * Returns the name of the school
     * @return String: school
     */
    public String getSchool() {
        return this.school;
    }

    /**
     * Returns the class year
     * @return int: classYear
     */
    public int getClassYear() {
        return this.classYear;
    }

    /**
     * Returns the Major
     * @return String: Major
     */
    public String getMajor() {
        return this.major;
    }

    /**
     * Return the minor
     * @return String: Minor
     */
    public String getMinor() {
        return this.minor;
    }

    /**
     * Return the gpa
     * @return double: gpa
     */
    public double getGpa() {
        return this.gpa;
    }

    /**
     * Return a String with the information of education
     * @return String of information
     */
    public String toString() {
        return this.school + "\nClass Year: " + this.classYear + "\nMajor: " + this.major +
        "\nMinor: " + this.minor + "\nGPA: " + this.gpa;
    }

}
