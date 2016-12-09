package action;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import model.DBHandler;

import model.User;
import model.School;
import model.User_DB;

/**
 *
 * @author recinocs
 */
public class EditProfile extends FetchSessionAware {
    private String name, email, username, password, confirm;
    private String uName, uEmail, uUsername, uPassword;
    private User curUser, user;
    private School school;
    
    private User_DB db = new User_DB();
    DBHandler db_h = new DBHandler();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String execute() {
        try {
            curUser = db.getUser((Integer)session.get("userId"));
            if(curUser == null)
                return "failure";

            if(name == null || name.equals("") || name.equals(curUser.getName()))
                uName = curUser.getName();
            else
                uName = name;
            
            if(email == null || email.equals("") || email.equals(curUser.getEmail()))
                uEmail = curUser.getEmail();
            else if(!email.contains("@") || !email.contains(".")) {
                addFieldError("email", "Invalid email. Please enter a valid email");
                return INPUT;
            }
            else if(db.checkEmail(email)) {
                addFieldError("email", "Email already exists. Please choose a different email.");
                return INPUT;
            }
            else
                uEmail = email;
            
            if(username == null || username.equals("") || username.equals(curUser.getUsername()))
                uUsername = curUser.getUsername();
             else if(username.length() < 6) {
                addFieldError("username", "Username must be at least 6 characters long.");
                return INPUT;
             }
            else if(!username.matches("^[a-zA-Z0-9!@#$_&]*$")) {
                addFieldError("username", "Username must contain only letters, numbers, !, @, #, &, or $.");
                return INPUT;
            }
            else if(db.checkUsername(username)) {
                addFieldError("username", "Username already exists. Please select a different username.");
                return INPUT;
            }
            else
                uUsername = username;
            
            if(password == null || password.equals(""))
                uPassword = "NULL";
            else if(hashPassword(password).equals(curUser.getPassword()))
                uPassword = "NULL";
            else if(password.length() < 8) {
                addFieldError("password", "Please enter a password at least 8 characters long.");
                return INPUT;
            }
            else if(!password.equals(confirm)) {
                addFieldError("password", "The two passwords must match.");
                return INPUT;
            }
            else
                uPassword = password;

            if(uPassword == null)
                uPassword = "NULL";
            uPassword = hashPassword(uPassword);
            
            user = new User(uPassword, uEmail, uName, uUsername, curUser.getRoleId(), curUser.getIsProfessor(), curUser.getSchoolId());
            db.updateUser((Integer)session.get("userId"), user);;
            school = db_h.getSchoolFromId(user.getSchoolId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return SUCCESS;
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
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
    
    
}
