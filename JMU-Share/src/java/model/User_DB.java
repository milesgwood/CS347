package model;

import java.util.*;
import java.sql.*;
/**
 * This class handles all database transactions for the User class.
 * 
 * @author Christopher Recinos
 * @version 2016-11-28
 */
public class User_DB {
    
    /**
     * 
     * @param user The user being added
     * @return whether or not the operation succeeded
    */
    
    private final DBHandler handler = new DBHandler();
    private Connection con;
    
    public boolean addUser(User user) {
        return true;
    }
    
    public boolean checkIfUserExists(User user) {
        boolean userFound = false;
        String username, email;
        try{
            if(!(handler.isOpen)) {   
                handler.open();
                con = handler.con;
            }
        
            username = user.getUsername();
            email = user.getEmail();
        
            String command = "SELECT * FROM user WHERE username=? OR email=?;";
            PreparedStatement ps = con.prepareStatement(command);
            ps.setString(1, username);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
               userFound = true; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFound;
    }
    
    public static void main(String[] args) {
        User user = new User("123456", "chrisrecinos38@gmail.com", "Christopher Recinos", "recinocs", false);
        System.out.println(new User_DB().checkIfUserExists(user));
    }
}
