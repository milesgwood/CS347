package action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import model.User_DB;
import model.User;
/**
 *
 * @author recinocs
 */
public class RegisterAction extends ActionSupport implements SessionAware {
   
    private SessionMap<String, Object> userSession;
    private User_DB db;
    
    private String name, email, username, password, confirm, school, hashedPassword;
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
        if(!email.contains("@") || !email.contains("."))
            addFieldError("email", "Invalid email. Please enter a valid email");
        else if(username.length() < 6)
            addFieldError("username", "Username must be at least 6 characters long.");
        else if(!username.matches("^[a-zA-Z0-9!@#$&]*$"))
            addFieldError("username", "Username must contain only letters, numbers, !, @, #, &, or $.");
        else if(password.length() < 8)
            addFieldError("password", "Please enter a password at least 8 characters long.");
        else if(!password.equals(confirm))
            addFieldError("password", "The two passwords must match."); 
    }
    
    public String execute() {
        return SUCCESS;
    }
    
    public void setSession(Map<String, Object> map) {
        userSession = (SessionMap)map;
    }
}
