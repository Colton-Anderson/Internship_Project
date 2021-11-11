/**
 *  Instance of a User 
 */
import java.util.Random;

public class User {

    protected String username;
    protected String password;
    protected String type;
    protected String uUID;
    private Random random = new Random();

    //Constructors
    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.uUID = createUUID();
    }

    public User(String username, String password, String type, String uUID) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.uUID = uUID;
    }

    /**
     * returns true if the username is correct
     * @param username
     * @return boolean
     */
    public boolean verifyUsername(String username) {
        if(this.username == username) {
            return true;
        }
        return false;
    }

    /**
     * retruns true if password is correct
     * @param password
     * @return boolean
     */
    public boolean verifyPassowrd(String password) {
        if(this.password == password) {
            return true;
        }
        return false;
    }

    //getters
    public String getUUID() {
        return this.uUID;
    }

    public String getUsername() {
        return this.username;
    }


    public String getPassword() {
        return this.password;
    }

    public String getType() {
        return this.type;
    }    

    /**
     * creates a random user ID
     * @return String
     */
    public String createUUID() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        return String.valueOf(alphabet.charAt(random.nextInt(alphabet.length())))+uUIDNumbers();
    }

    /**
     * Create random numbers for the UUID
     * @return String
     */
    private String uUIDNumbers() {
        String ret = "";
        for(int i=0;i<6;i++) {
            ret += random.nextInt(9);
        }
        return ret;
    }

    /**
     * Returns a String of the Username, password, and UUID
     * @returns String
     */
    public String toString() {
        return "Username"+this.username+"\nPassword"+this.password+"\nuUID"+this.uUID;
    }
}
