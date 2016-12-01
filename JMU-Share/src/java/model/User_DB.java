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
        boolean userAdded = false;
        
        try {
            if(!checkIfUserExists(user)) {
                
                String name, username, password, email;
                boolean isProfessor;
                int roleId, schoolId; 
                
                String insert_sql = "INSERT INTO user VALUES(null, ?, ?, ?, ?, ?, ?, ?)"; 
                
                PreparedStatement ps = con.prepareStatement(insert_sql);
		
		name = user.getName();
		username = user.getUsername();
		password = user.getPassword();
		email = user.getEmail();
		isProfessor = user.isProfessor();
		roleId = user.getRoleId();
		schoolId = user.getSchoolId();

		ps.setString(1, password);
		ps.setString(2, email);
		ps.setString(3, name);
		ps.setString(4, username);
		ps.setInt(5, roleId);
		ps.setBoolean(6, isProfessor);
		ps.setInt(7, schoolId);

		ps.executeUpdate();

		userAdded = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
        return userAdded;
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
        User user = new User("654321", "kesterSON@gmail.com", "Dumbledore", "dbubbs", 2, false, 11);
        System.out.println(new User_DB().addUser(user));
    }
}
