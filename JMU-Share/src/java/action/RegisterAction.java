package action;

import com.opensymphony.xwork2.ActionSupport;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import model.User_DB;
import model.School_DB;
import model.School;
import model.User;
/**
 *
 * @author recinocs
 */
public class RegisterAction extends ActionSupport implements SessionAware {
   
    private SessionMap<String, Object> userSession;
    private User_DB db = new User_DB();
    private School_DB sDB = new School_DB();
    private User user;
    private School school_ob;
    private int school_id;
    
    private String name, email, username, password, confirm, school, hashPassword, role;
    private boolean isProfessor;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSchool() {
        return school;
    }

    public boolean isIsProfessor() {
        return isProfessor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setIsProfessor(boolean isProfessor) {
        this.isProfessor = isProfessor;
    }

    public String getConfirm() {
        return confirm;
    }
    
    public void validate() {
        try {
            if(name == null || name.equals(""))
                addFieldError("name", "Please enter a name.");
            else if(!email.contains("@") || !email.contains("."))
                addFieldError("email", "Invalid email. Please enter a valid email");
            else if(db.checkEmail(email))
                addFieldError("email", "Email already exists. Please choose a different email.");
            else if(username.length() < 6)
                addFieldError("username", "Username must be at least 6 characters long.");
            else if(!username.matches("^[a-zA-Z0-9!@#$&]*$"))
                addFieldError("username", "Username must contain only letters, numbers, !, @, #, &, or $.");
            else if(db.checkUsername(username))
                addFieldError("username", "Username already exists. Please select a different username.");
            else if(password.length() < 8)
                addFieldError("password", "Please enter a password at least 8 characters long.");
            else if(!password.equals(confirm))
                addFieldError("password", "The two passwords must match.");
        } catch (SQLException e) {
           System.out.println("ERROR");
        }
    }
    
    public String execute() {
        try {
            hashPassword = hashPassword(password);
            school_ob = sDB.getSchool(school);
            school_id = sDB.getIdForSchool(school_ob);
            user = new User(hashPassword, email, name, username, 2, isProfessor, school_id);
            if(db.addUser(user)) {
                userSession.put("logged_in", true);
                userSession.put("userId", db.getIdForUser(user));
                userSession.put("role", "USER");
            }
            else {
                return "FAILURE";
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public void setSession(Map<String, Object> map) {
        userSession = (SessionMap)map;
    }
    
    private String hashPassword(String password) {
        String hashedPassword = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.reset();
            byte[] bytes = md.digest(password.getBytes());
            hashedPassword = new BigInteger(1, bytes).toString(16);
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return hashedPassword;
    }
}
