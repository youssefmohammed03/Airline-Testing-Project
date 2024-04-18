package airline;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected String username;
    protected String password;

    public static List<User> users = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean signup(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username)) {
                System.out.println("Username already exists. Please try again.");
                return false; 
            }
        }
        return true;
    }
}