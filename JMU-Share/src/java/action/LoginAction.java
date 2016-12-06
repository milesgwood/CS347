package action;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.Map;
import model.User_DB;
import model.User;

public class LoginAction extends ActionSupport {
    private String username, password;

    public User_DB db = new User_DB();
    public User user = null;
    
    private Map<String, Object> userSession;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public void validate() {
        try{
            if(username == null || username.equals(""))
                addFieldError("username", "The username field cannot be blank.");
            else if(password == null || password.equals(""))
                addFieldError("password", "The password field cannot be blank.");
            else {
                user = db.getUser(username, password);
            
                if(user == null)
                    addFieldError("username", "Invalid username/password combination.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String execute() {
        return SUCCESS;
    }
}
