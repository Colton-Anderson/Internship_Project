/**
 * @author Lauren Hadlow, Colton Anderson, Mitchell Carroll
 * Creates the Job Listing List
 */
import java.util.ArrayList;

public class JobListingsList {

    private static ArrayList<JobListing> jobListings;
    private static JobListingsList jobListingList;

    /**
     * constructor
     */
    public JobListingsList() {
        if(DataLoader.getJobListings() != null) {
            jobListings = DataLoader.getJobListings();
        }
        else {
            jobListings = new ArrayList<JobListing>();
        }
    }

    /**
     * creates a list of employers
     * @param employers
     */
    public void setEmployers(ArrayList<Employer> employers) {
        for(Employer employer : employers) {
            for(JobListing listing : jobListings) {
                if(listing.getEmployerID().equalsIgnoreCase(employer.getUUID())) {
                    listing.setEmployer(employer);
                }
            }
        }
    }

    /**
     * returns an instance of the job listing list
     * @return
     */
    public static JobListingsList getInstance() {
        if(jobListingList == null) {
            return new JobListingsList();
        }
        return jobListingList;
    }

    /**
     * returns the joblistings
     * @return
     */
    public ArrayList<JobListing> getJobListings() {
        return jobListings;
    }

    /**
     * adds a job Listing
     * @param jobListing
     */
    public void addListing(JobListing jobListing) {
        jobListings.add(jobListing);
    }

    public static void updateListing(JobListing listing) {
        for(int i=0;i<jobListings.size();i++) {
            if(jobListings.get(i).getUUID().equalsIgnoreCase(listing.getUUID())) {
                jobListings.remove(jobListings.get(i));
                jobListings.add(listing);
            }
        }
    }

    /**
     * Searches for a job listing using key words
     * @param keyWord
     * @return
     */
    public ArrayList<JobListing> searchListings(String keyWord) {
        ArrayList<JobListing> ret = new ArrayList<JobListing>();
        for(int i=0;i<jobListings.size();i++) {
            if(jobListings.get(i).toString().toLowerCase().contains(keyWord.toLowerCase())) {
                ret.add(jobListings.get(i));
            }
        }
        return ret;
    }

    /**
     * checks if a job is already listed
     * @param jobListing
     * @return
     */
    public boolean contains(JobListing jobListing) {
        for(int i=0;i<jobListings.size();i++) {
            if(jobListings.get(i) == jobListing) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if a keword is within a string
     * @param string
     * @param keyWord
     * @return
     */
    public boolean containsString(String string, String keyWord) {
        if(string.contains(keyWord)) {
            return true;
        }
        return false;
    }

    /**
     * Deletes the job Listing from the list
     */
    public void deleteListing(JobListing jobListing) {
        jobListings.remove(jobListing);
    }
}
