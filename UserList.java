/**
 * @author Lauren Hadlow, Colton Anderson, Mitchell Carroll
 * Creates the User List
 */
import java.util.ArrayList;

public class UserList {
    private static UserList userList;
    private static ArrayList<User> users;
    private static ArrayList<Student> students;
    private static ArrayList<Employer> employers;
    private static ArrayList<Admin> admins;

    /**
     * Constructor
     */
    private UserList() {
        if(DataLoader.getUsers() != null) {
            users = DataLoader.getUsers();
        }
        else {
            users = new ArrayList<User>();
        }
        students = setStudents();
        employers = setEmployers();
        admins = setAdmins();
    }

    /**
     * loads the users from the json files
     * @return students
     */
    private ArrayList<Student> setStudents() {
        if(DataLoader.getStudents() == null) {
            return new ArrayList<Student>();
        }
        return DataLoader.getStudents();
    }

    public void addListing(JobListing listing) {
        for(Employer employer : employers) {
            if(listing.getEmployerID().equals(employer.getUUID())) {
                employer.addListing(listing);
            }
        }
    }

    private ArrayList<Employer> setEmployers() {
        if(DataLoader.getEmployers() == null) {
            return new ArrayList<Employer>();
        }
        ArrayList<Employer> aemployers = DataLoader.getEmployers();
        setEmployerListings(aemployers);
        return aemployers;
    }

    private void setEmployerListings(ArrayList<Employer> aemployers) {
        for(JobListing listing : DataLoader.getJobListings()) {
            for(Employer employer : aemployers) {
                if(listing.getEmployerID().equalsIgnoreCase(employer.getUUID())) {
                    employer.addListing(listing);
                }
            }
        }
    }

    /**
     * loads the admin from the json files
     * @return admin
     */
    private ArrayList<Admin> setAdmins() {
        if(DataLoader.getAdmins() == null) {
            return new ArrayList<Admin>();
        }
        return DataLoader.getAdmins();
    }

    /**
     * Creates an Instance of the the class
     * @return
     */
    public static UserList getInstance() {
        if(userList == null) {
            return new UserList();
        }
        return userList;
    }

    /**
     * Checks if the username and password are matched to ones in the list
     * @param username
     * @param password
     * @return user
     */
    public User login(String username, String password) {
        for(int i=0;i<users.size();i++) {
            if(users.get(i).getUsername().equalsIgnoreCase(username) && users.get(i).getPassword().equalsIgnoreCase(password)) {
                return users.get(i);
            }
        }
        return null;
    }

    /**
     * finds the User
     * @param uUID
     * @return
     */
    public User findUser(String uUID) {
        for(int i=0;i<users.size();i++) {
            if(users.get(i).getUUID() == uUID) {
                return users.get(i);
            }
        }
        return null;
    }

    /**
     * finds the type of user
     * @return
     */
    public User findType() {
        return null;
    }

    /**
     * finds the account within the userlist
     * @param username
     * @param password
     * @return
     */
    public boolean findAccount(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUsername() == username && users.get(i).getPassword() == password) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds if the user is already in the program
     * @param username
     * @return
     */
    public boolean containsUser(String username) {
        for(int i=0;i<users.size();i++) {
            if(users.get(i).getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    //getters
    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Employer> getEmployers() {
        return employers;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    /**
     * adds a resume to a student account
     * @param student
     * @param resume
     */
    public void addResume(Student student, Resume resume) {
        for(Student aStudent : students) {
            if(aStudent.getUUID().equalsIgnoreCase(student.getUUID())) {
                aStudent.addResume(resume);
            }
        }
    }

    /**
     * adds a student to the list of users and the list of students
     * @param student
     */
    public void addStudent(Student student) {
        users.add(student);
        students.add(student);
    }

    /**
     * adds an employer to the list of users and the list of employers
     * @param employer
     */
    public void addEmployer(Employer employer) {
        users.add(employer);
        employers.add(employer);
    }

}
