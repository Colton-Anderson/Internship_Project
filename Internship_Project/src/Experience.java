/**
 * @Author Colton Anderson
 * This Class Contain the Information for an Experience
 */
public class Experience {

    private String title;
    private String startdate;
    private String endDate;
    private String description;

    /**
     * Constructs the experience
     * @param title: title of the experience
     * @param startdate: start date of the experience
     * @param endDate: end date of the experience
     * @param description: description of the experience
     */
    public Experience(String title, String startdate, String endDate, String description) {
        this.title = title;
        this.startdate = startdate;
        this.endDate = endDate;
        this.description = description;
    }

    /**
     * Returns a string with the experience information
     * @return String: Returns the string containing the Experience information
     */
    public String toString() {
        return title+"\nStart Date: "+startdate+"\nEnd Date: "+endDate+"\nDescription: "+description;
    }
    

    /**
     * Returns a String of the title
     * @return String: title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns a String of the start date
     * @return String: startDate
     */
    public String getStartDate() {
        return this.startdate;
    }

    /**
     * Return a String of the end date
     * @return String: endDate
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * Return a String of the Description
     * @return String: description
     */
    public String getDescription() {
        return this.description;
    }
}
